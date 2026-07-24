package net.midpoint.visuals.gui;

import net.midpoint.visuals.config.ConfigManager;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class MidpointGuiScreen extends Screen {

    public MidpointGuiScreen() {
        super(Component.literal("Midpoint Visuals"));
    }

    @Override
    protected void init() {
        int buttonWidth = 160;
        int buttonHeight = 24; // Увеличенная высота кнопок для удобных тапов
        int spacing = 6;
        int startX = this.width / 2 - buttonWidth / 2;
        int startY = this.height / 2 - 120;

        // Кнопка 1: Духи и Кубики
        this.addRenderableWidget(Button.builder(Component.literal("Spirits: " + getStateLabel(ConfigManager.spiritsState)), button -> {
            ConfigManager.spiritsState = (ConfigManager.spiritsState + 1) % 3;
            button.setMessage(Component.literal("Spirits: " + getStateLabel(ConfigManager.spiritsState)));
        }).bounds(startX, startY, buttonWidth, buttonHeight).build());

        // Кнопка 2: Quantum Link
        this.addRenderableWidget(Button.builder(Component.literal("Quantum Link: " + getStateLabel(ConfigManager.quantumLinkState)), button -> {
            ConfigManager.quantumLinkState = (ConfigManager.quantumLinkState + 1) % 3;
            button.setMessage(Component.literal("Quantum Link: " + getStateLabel(ConfigManager.quantumLinkState)));
        }).bounds(startX, startY + (buttonHeight + spacing), buttonWidth, buttonHeight).build());

        // Кнопка 3: Траектория перла
        this.addRenderableWidget(Button.builder(Component.literal("Pearl Tracker: " + getStateLabel(ConfigManager.trackerEndJState)), button -> {
            ConfigManager.trackerEndJState = (ConfigManager.trackerEndJState + 1) % 3;
            button.setMessage(Component.literal("Pearl Tracker: " + getStateLabel(ConfigManager.trackerEndJState)));
        }).bounds(startX, startY + (buttonHeight + spacing) * 2, buttonWidth, buttonHeight).build());

        // Кнопка 4: Подсветка блоков
        this.addRenderableWidget(Button.builder(Component.literal("Block Highlight: " + getStateLabel(ConfigManager.blockHighlightState)), button -> {
            ConfigManager.blockHighlightState = (ConfigManager.blockHighlightState + 1) % 3;
            button.setMessage(Component.literal("Block Highlight: " + getStateLabel(ConfigManager.blockHighlightState)));
        }).bounds(startX, startY + (buttonHeight + spacing) * 3, buttonWidth, buttonHeight).build());

        // Кнопка 5: Автоспринт (True/False)
        this.addRenderableWidget(Button.builder(Component.literal("AutoSprint: " + (ConfigManager.autoSprintEnabled ? "ON" : "OFF")), button -> {
            ConfigManager.autoSprintEnabled = !ConfigManager.autoSprintEnabled;
            button.setMessage(Component.literal("AutoSprint: " + (ConfigManager.autoSprintEnabled ? "ON" : "OFF")));
        }).bounds(startX, startY + (buttonHeight + spacing) * 4, buttonWidth, buttonHeight).build());

        // Кнопка 6: Анти-лаг Кристаллы/Якоря
        this.addRenderableWidget(Button.builder(Component.literal("PvP Anti-Lag: " + (ConfigManager.pvpAntiLagState > 0 ? "ON" : "OFF")), button -> {
            ConfigManager.pvpAntiLagState = ConfigManager.pvpAntiLagState == 0 ? 1 : 0;
            button.setMessage(Component.literal("PvP Anti-Lag: " + (ConfigManager.pvpAntiLagState > 0 ? "ON" : "OFF")));
        }).bounds(startX, startY + (buttonHeight + spacing) * 5, buttonWidth, buttonHeight).build());
    }

    private String getStateLabel(int state) {
        if (state == 1) return "ON";
        if (state == 2) return "RANDOM";
        return "OFF";
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // Отрезаем дефолтный задний фон майнкрафта, заливаем кастомным матовым черным цвет #0D0D0D
        guiGraphics.fill(0, 0, this.width, this.height, 0xFF0D0D0D);
        
        guiGraphics.drawCenteredString(this.font, "MIDPOINT VISUALS", this.width / 2, this.height / 2 - 145, 0xFFFFFFFF);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean isPauseScreen() {
        return false; // Игра не встает на паузу в одиночном режиме при открытии меню
    }
  }
