package com.midpoint.midpoint_visuals;

import com.midpoint.midpoint_visuals.config.MidpointConfig;
import com.midpoint.midpoint_visuals.gui.MidpointGuiScreen;
import com.midpoint.midpoint_visuals.modules.block_highlight.BlockHighlightRenderer;
import com.midpoint.midpoint_visuals.modules.cosmetics.CosmeticsRenderer;
import com.midpoint.midpoint_visuals.modules.quantum_link_reach.QuantumLinkRenderer;
import com.midpoint.midpoint_visuals.modules.realistic_spirits_cubes.SpiritCubeRenderer;
import com.midpoint.midpoint_visuals.modules.tracker_endj.PearlTrackerRenderer;
import com.midpoint.midpoint_visuals.registry.ParticleRegistry;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class MidpointVisualsClient implements ClientModInitializer {

    private static KeyBinding openGuiKeybinding;

    @Override
    public void onInitializeClient() {
        MidpointVisuals.LOGGER.info("Midpoint Visuals Client initialized!");

        // Register config
        AutoConfig.register(MidpointConfig.class, JanksonConfigSerializer::new);

        // Register particles
        ParticleRegistry.registerParticles();

        // Register renderers
        new SpiritCubeRenderer();
        new QuantumLinkRenderer();
        new PearlTrackerRenderer();
        new BlockHighlightRenderer();
        new CosmeticsRenderer();

        // Register keybinding for GUI
        openGuiKeybinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.midpoint_visuals.opengui",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.midpoint_visuals.general"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openGuiKeybinding.wasPressed()) {
                if (client.currentScreen == null) {
                    client.setScreen(new MidpointGuiScreen(Text.literal("Midpoint Visuals")));
                } else if (client.currentScreen instanceof MidpointGuiScreen) {
                    client.setScreen(null);
                }
            }
        });
    }
}
