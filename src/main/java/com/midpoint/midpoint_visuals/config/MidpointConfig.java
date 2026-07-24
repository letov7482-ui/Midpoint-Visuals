package com.midpoint.midpoint_visuals.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import com.midpoint.midpoint_visuals.MidpointVisuals;

@Config(name = MidpointVisuals.MOD_ID)
public class MidpointConfig implements ConfigData {

    @ConfigEntry.Category("realistic_spirits_cubes")
    @ConfigEntry.Gui.TransitiveObject
    public RealisticSpiritsCubes realisticSpiritsCubes = new RealisticSpiritsCubes();

    @ConfigEntry.Category("quantum_link_reach")
    @ConfigEntry.Gui.TransitiveObject
    public QuantumLinkReach quantumLinkReach = new QuantumLinkReach();

    @ConfigEntry.Category("tracker_endj")
    @ConfigEntry.Gui.TransitiveObject
    public TrackerEndJ trackerEndJ = new TrackerEndJ();

    @ConfigEntry.Category("block_highlight")
    @ConfigEntry.Gui.TransitiveObject
    public BlockHighlight blockHighlight = new BlockHighlight();

    @ConfigEntry.Category("nick_changer")
    @ConfigEntry.Gui.TransitiveObject
    public NickChanger nickChanger = new NickChanger();

    @ConfigEntry.Category("client_dances")
    @ConfigEntry.Gui.TransitiveObject
    public ClientDances clientDances = new ClientDances();

    @ConfigEntry.Category("hit_color")
    @ConfigEntry.Gui.TransitiveObject
    public HitColor hitColor = new HitColor();

    @ConfigEntry.Category("pvp_anti_lag")
    @ConfigEntry.Gui.TransitiveObject
    public PvPAntiLag pvpAntiLag = new PvPAntiLag();

    @ConfigEntry.Category("fog_settings")
    @ConfigEntry.Gui.TransitiveObject
    public FogSettings fogSettings = new FogSettings();

    @ConfigEntry.Category("cosmetics")
    @ConfigEntry.Gui.TransitiveObject
    public Cosmetics cosmetics = new Cosmetics();

    @ConfigEntry.Category("utility")
    @ConfigEntry.Gui.TransitiveObject
    public Utility utility = new Utility();

    // ====================== SUB-CLASSES ======================

    public static class RealisticSpiritsCubes {
        @Comment("Enable Realistic Spirits & Cubes")
        public boolean enabled = false;
        @Comment("Spirits vanish after X seconds without hit")
        public int spiritVanishTime = 10;
        @Comment("Color of the static cubes (ARGB)")
        public int cubeColor = 0xFFFFFFFF; // White
        public enum State { OFF, ON, RANDOM }
        public State state = State.OFF;
    }

    public static class QuantumLinkReach {
        @Comment("Enable Quantum Link + Reach")
        public boolean enabled = false;
        @Comment("Laser line color (ARGB)")
        public int lineColor = 0xFF00FFFF; // Cyan
        @Comment("Text color (ARGB)")
        public int textColor = 0xFFFFFFFF; // White
        @Comment("Line thickness")
        public float lineThickness = 1.5f;
        public enum State { OFF, ON, RANDOM }
        public State state = State.OFF;
    }

    public static class TrackerEndJ {
        @Comment("Enable Ender Pearl trajectory line + landing spot")
        public boolean enabled = false;
        @Comment("Trajectory line color (ARGB)")
        public int lineColor = 0xFF00FF00; // Green
        @Comment("Landing circle color (ARGB)")
        public int circleColor = 0xFF00FF00; // Green
        @Comment("Line thickness")
        public float lineThickness = 1.0f;
        public enum State { OFF, ON, RANDOM }
        public State state = State.OFF;
    }

    public static class BlockHighlight {
        @Comment("Enable Block Highlight")
        public boolean enabled = false;
        @Comment("Outline color (ARGB)")
        public int outlineColor = 0xFF00FFFF; // Cyan
        @Comment("Face fill color (ARGB)")
        public int fillColor = 0x4000FFFF; // Semi-transparent Cyan
        public enum SubMode { NONE, TRACER, COSMIC_VIEW }
        public SubMode subMode = SubMode.NONE;
        public enum State { OFF, ON, RANDOM }
        public State state = State.OFF;
    }

    public static class NickChanger {
        @Comment("Enable Client-side local name bypass")
        public boolean enabled = false;
        @Comment("Your custom nickname")
        public String nickname = "Midpoint";
        public enum State { OFF, ON, RANDOM }
        public State state = State.OFF;
    }

    public static class ClientDances {
        @Comment("Enable Client-side local animations")
        public boolean enabled = false;
        public enum DanceType { NONE, DANCE_1, DANCE_2 }
        public DanceType currentDance = DanceType.NONE;
        public enum State { OFF, ON, RANDOM }
        public State state = State.OFF;
    }

    public static class HitColor {
        @Comment("Enable custom hurt flash color")
        public boolean enabled = false;
        public enum ColorType { SOFT_WHITE, YELLOW, CUSTOM_RED }
        public ColorType colorType = ColorType.SOFT_WHITE;
        @Comment("Custom red color (ARGB)")
        public int customRedColor = 0xFFFF0000; // Red
        public enum State { OFF, ON, RANDOM }
        public State state = State.OFF;
    }

    public static class PvPAntiLag {
        @Comment("Disable crystal/anchor explosion flashes and heavy particles")
        public boolean enabled = false;
        @Comment("Optimize Ender Pearl render")
        public boolean optimizePearlRender = true;
        public enum State { OFF, ON, RANDOM }
        public State state = State.OFF;
    }

    public static class FogSettings {
        @Comment("Enable custom atmospheric fog")
        public boolean enabled = false;
        public enum FogPreset { NONE, PRESET_1, PRESET_2, PRESET_3, PRESET_4 }
        public FogPreset currentPreset = FogPreset.NONE;
        @Comment("Fog color for Preset 1 (ARGB)")
        public int preset1Color = 0xFF808080; // Gray
        @Comment("Fog color for Preset 2 (ARGB)")
        public int preset2Color = 0xFFADD8E6; // Light Blue
        @Comment("Fog color for Preset 3 (ARGB)")
        public int preset3Color = 0xFF4682B4; // Steel Blue
        @Comment("Fog color for Preset 4 (ARGB)")
        public int preset4Color = 0xFF6A5ACD; // Slate Blue
        public enum State { OFF, ON, RANDOM }
        public State state = State.OFF;
    }

    public static class Cosmetics {
        @Comment("Enable Head 3D model placeholder (Monkey on a mountain)")
        public boolean enabled = false;
        @Comment("Auto-disable if FPS drops below threshold")
        public boolean autoDisableOnLowFps = true;
        @Comment("FPS threshold for auto-disable")
        public int fpsThreshold = 30;
        public enum State { OFF, ON, RANDOM }
        public State state = State.OFF;
    }

    public static class Utility {
        @Comment("Enable Utility features")
        public boolean enabled = false;
        @Comment("Keybind for 'gg' message (B key)")
        public boolean ggKeybind = true;
        @Comment("ClientPlayerEntity AutoSprint")
        public boolean autoSprint = true;
        public enum State { OFF, ON, RANDOM }
        public State state = State.OFF;
    }

    public static MidpointConfig get() {
        return AutoConfig.getConfigHolder(MidpointConfig.class).getConfig();
    }
                           }
