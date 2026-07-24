package net.midpoint.visuals.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.midpoint.visuals.config.ConfigManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

public class QuantumLinkRenderer {
    private static Entity lastTarget = null;
    private static long hitTimestamp = 0;

    public static void logHit(Entity target) {
        lastTarget = target;
        hitTimestamp = System.currentTimeMillis();
    }

    public static void render(PoseStack poseStack, MultiBufferSource bufferSource, float partialTicks) {
        if (ConfigManager.quantumLinkState == 0 || lastTarget == null) return;
        
        // Линия горит ровно 0.2 секунды (200 миллисекунд) после удара
        if (System.currentTimeMillis() - hitTimestamp > 200) {
            lastTarget = null;
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        // Позиция игрока и цели с учетом интерполяции кадров (для плавности на 60+ FPS)
        Vec3 playerPos = mc.player.getPosition(partialTicks).add(0, mc.player.getEyeHeight(), 0);
        Vec3 targetPos = lastTarget.getPosition(partialTicks).add(0, lastTarget.getBbHeight() / 2f, 0);
        
        double distance = playerPos.distanceTo(targetPos);

        // Переводим мировые координаты в координаты рендера камеры
        Vec3 cameraPos = mc.gameRenderer.getMainCamera().getPosition();
        float x1 = (float) (playerPos.x - cameraPos.x);
        float y1 = (float) (playerPos.y - cameraPos.y);
        float z1 = (float) (playerPos.z - cameraPos.z);

        float x2 = (float) (targetPos.x - cameraPos.x);
        float y2 = (float) (targetPos.y - cameraPos.y);
        float z2 = (float) (targetPos.z - cameraPos.z);

        // 1. Отрисовка лазерной нити связи
        VertexConsumer lineBuffer = bufferSource.getBuffer(RenderType.lines());
        Matrix4f matrix = poseStack.last().pose();

        // Извлекаем цвета из менеджера конфигурации
        int color = ConfigManager.quantumLineColor;
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;

        lineBuffer.addVertex(matrix, x1, y1, z1).setColor(r, g, b, 180).setNormal(0, 1, 0);
        lineBuffer.addVertex(matrix, x2, y2, z2).setColor(r, g, b, 180).setNormal(0, 1, 0);

        // 2. Отображение текста дистанции ровно посередине лазера
        float midX = (x1 + x2) / 2f;
        float midY = (y1 + y2) / 2f + 0.1f; // Слегка приподнимаем над лазером, чтобы не сливалось
        float midZ = (z1 + z2) / 2f;

        poseStack.pushPose();
        poseStack.translate(midX, midY, midZ);
        
        // Билборд-эффект: текст принудительно разворачивается лицом к камере игрока
        poseStack.mulPose(mc.gameRenderer.getMainCamera().rotation());
        poseStack.scale(-0.025f, -0.025f, 0.025f); // Маленький, аккуратный размер шрифта

        String distanceText = String.format("%.2fm", distance);
        float textWidth = mc.font.width(distanceText);
        
        // Рендерим компактный текст с едва заметной тенью (0x20000000 — полупрозрачный черный подклад)
        mc.font.drawInBatch(distanceText, -textWidth / 2f, 0, ConfigManager.quantumTextColor, false, poseStack.last().pose(), bufferSource, Minecraft.FontDisplayMode.NORMAL, 0x20000000, 15728880);
        
        poseStack.popPose();
    }
}
