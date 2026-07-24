package net.midpoint.visuals.mixin;

import net.midpoint.visuals.config.ConfigManager;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public class AutoSprintMixin {
    @Inject(method = "aiStep", at = @At("HEAD"))
    private void injectAutoSprint(CallbackInfo ci) {
        LocalPlayer player = (LocalPlayer) (Object) this;
        // Если модуль включен и игрок движется вперед — принудительно включаем спринт
        if (ConfigManager.autoSprintEnabled && player.input.hasForwardImpulse() && !player.isShiftKeyDown()) {
            player.setSprinting(true);
        }
    }
}
