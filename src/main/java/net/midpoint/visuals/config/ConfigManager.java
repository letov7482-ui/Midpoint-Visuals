package net.midpoint.visuals.config;

public class ConfigManager {
    // Состояния: 0 = ВЫКЛ, 1 = ВКЛ, 2 = РАНДОМ
    public static int spiritsState = 0;
    public static int quantumLinkState = 0;
    public static int trackerEndJState = 0;
    public static int blockHighlightState = 0;
    public static int nickChangerState = 0;
    public static int clientDancesState = 0;
    public static int hitColorState = 0;
    public static int pvpAntiLagState = 0;
    public static int fogSettingsState = 0;
    public static int cosmeticsState = 0;
    
    // Дополнительные параметры утилит
    public static boolean autoSprintEnabled = false;
    public static boolean utilityBindEnabled = false;
    
    // Кастомные строки и настройки цвета (HEX)
    public static String customNick = "MidpointPlayer";
    public static int quantumLineColor = 0x00FFFFFF; // Неоново-белый по дефолту
    public static int quantumTextColor = 0xFFFFFF00; // Желтый текст метров по дефолту
    public static int pearlTrackerColor = 0xFF00FF00; // Зеленый круг приземления перла

    public static void loadConfig() {
        // Изначально абсолютно все false/0, как ты и просил.
        // Здесь можно дописать сохранение/чтение в JSON файл на диске
    }

    public static void saveConfig() {
        // Логика записи настроек в файл конфигурации
    }
}

