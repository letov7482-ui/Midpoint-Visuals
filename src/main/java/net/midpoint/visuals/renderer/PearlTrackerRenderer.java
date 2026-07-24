package net.midpoint.visuals.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.midpoint.visuals.config.ConfigManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

public class PearlTrackerRenderer {

    public static void render(PoseStack poseStack, MultiBufferSource bufferSource, float partialTicks) {
        if (ConfigManager.trackerEndJState == 0) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return;

        Vec3 cameraPos = mc.gameRenderer.getMainCamera().getPosition();
        VertexConsumer buffer = bufferSource.getBuffer(RenderType.lines());
        Matrix4f matrix = poseStack.last().pose();

        int color = ConfigManager.pearlTrackerColor;
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;

        // Ищем все летящие жемчужины Энда в мире
        for (var entity : mc.level.entitiesForRendering()) {
            if (entity instanceof ThrownEnderpearl pearl) {
                Vec3 currentPos = pearl.getPosition(partialTicks);
                
                // Просчитываем падение (упрощенная симуляция вектора движения)
                Vec3 velocity = pearl.getDeltaMovement();
                Vec3 nextPos = currentPos.add(velocity);

                float x1 = (float) (currentPos.x - cameraPos.x);
                float y1 = (float) (currentPos.y - cameraPos.y);
                float z1 = (float) (currentPos.z - cameraPos.z);

                float x2 = (float) (nextPos.x - cameraPos.x);
                float y2 = (float) (nextPos.y - cameraPos.y);
                float z2 = (float) (nextPos.z - cameraPos.z);

                // Рисуем направляющий трек полета
                buffer.addVertex(matrix, x1, y1, z1).setColor(r, g, b, 255).setNormal(0, 1, 0);
                buffer.addVertex(matrix, x2, y2, z2).setColor(r, g, b, 255).setNormal(0, 1, 0);

                // Логика отрисовки круга приземления на блоке под перлом
                renderLandingCircle(poseStack, buffer, x2, y2 - 0.5f, z2, r, g, b);
            }
        }
    }

    private static void renderLandingCircle(PoseStack poseStack, VertexConsumer buffer, float x, float y, float z, int r, int g, int b) {
        Matrix4f matrix = poseStack.last().pose();
        float radius = 0.3f;
        int points = 16; // Сглаженность круга (16 углов идеально для оптимизации мобилок)

        for (int i = 0; i < points; i++) {
            float angle1 = (float) (i * 2 * Math.PI / points);
            float angle2 = (float) ((i + 1) * 2 * Math.PI / points);

            float px1 = x + (float) Math.cos(angle1) * radius;
            float pz1 = z + (float) Math.sin(angle1) * radius;
            float px2 = x + (float) Math.cos(angle2) * radius;
            float pz2 = z + (float) Math.sin(angle2) * radius;

            buffer.addVertex(matrix, px1, y, pz1).setColor(r, g, b, 200).setNormal(0, 1, 0);
            buffer.addVertex(matrix, px2, y, pz2).setColor(r, g, b, 200).setNormal(0, 1, 0);
        }
    }
}

