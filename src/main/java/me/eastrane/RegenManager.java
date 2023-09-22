package me.eastrane;

import me.eastrane.commands.rmCommand;
import me.eastrane.commands.rmCommandTab;
import me.eastrane.listeners.PlayerJoinListener;
import me.eastrane.listeners.RegainHealthListener;
import me.eastrane.methods.RegainHealthMethod;
import me.eastrane.utilities.ConfigManager;
import me.eastrane.utilities.LanguageManager;
import org.bukkit.plugin.java.JavaPlugin;
import me.eastrane.listeners.FoodLevelChangeListener;

import java.util.Objects;

public final class RegenManager extends JavaPlugin {

    private static RegenManager plugin;
    @Override
    public void onEnable() {
        plugin = this;
        new ConfigManager();
        new LanguageManager();

        Objects.requireNonNull(getCommand("regenmanager")).setExecutor(new rmCommand());
        Objects.requireNonNull(getCommand("regenmanager")).setTabCompleter(new rmCommandTab());
        boolean disableHunger = this.getConfig().getBoolean("disable-hunger");
        if (disableHunger) {
            new FoodLevelChangeListener(this);
        }
        new PlayerJoinListener(this);
        new RegainHealthListener(this);
        new RegainHealthMethod();
    }

    public static RegenManager pluginGet() {
        return plugin;
    }

//    @Override
//    public void onDisable() {
//    }
}