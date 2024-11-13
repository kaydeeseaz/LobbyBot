package com.kaydeesea.bot;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static FileConfiguration config;
    public static Main instance;
    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;
        config = getConfig();
        saveDefaultConfig();
        Bot.load();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}