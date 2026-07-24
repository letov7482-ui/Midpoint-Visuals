package net.midpoint.visuals.mixin;

import net.midpoint.visuals.config.ConfigManager;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntityRenderer.class)
public class HurtColorMixin {
    @Inject(method = "getOverlayCoords", at = @At("HEAD"), cancellable = true)
    private static void injectHurtColor(org.minecraft.world.entity.LivingEntity entity, float u, CallbackInfoReturnable<Integer> cir) {
        if (ConfigManager.hitColorState != 0 && entity.hurtTime > 0) {
            // Если включен белый цвет (пресет 1)
            if (ConfigManager.hitColorState == 1) {
                cir.setReturnValue(net.minecraft.client.renderer.texture.OverlayTexture.pack(
                    net.minecraft.client.renderer.texture.OverlayTexture.u(u), 
                    net.minecraft.client.renderer.texture.OverlayTexture.v(true) // Белый оверлей
                ));
            }
        }
    }
}
