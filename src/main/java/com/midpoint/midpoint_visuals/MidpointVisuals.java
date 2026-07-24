package com.midpoint.midpoint_visuals;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MidpointVisuals implements ModInitializer {

    public static final String MOD_ID = "midpoint_visuals";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Midpoint Visuals initialized! (Fabric 1.21.4 - loom 1.8)");
        // Здесь можно добавить дальнейшую инициализацию:
        // ParticleRegistry.registerParticles();
        // Config.register();
    }
}
