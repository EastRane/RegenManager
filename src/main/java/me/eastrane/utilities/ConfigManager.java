package me.eastrane.utilities;

import me.eastrane.RegenManager;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


public class ConfigManager {

    static int regeneration_period;
    static int regeneration_delay;

    public static int getRegeneration_period() {
        return regeneration_period;
    }

    public static int getRegeneration_delay() {
        return regeneration_delay;
    }

    public static boolean getDisable_hunger() {
        return disable_hunger;
    }

    static boolean disable_hunger;
    static String language;
    private static final RegenManager plugin = RegenManager.pluginGet();

    public ConfigManager() {
        plugin.saveDefaultConfig();
        replaceConfig();
        loadConfig();
    }
    public static void loadConfig() {
        language = plugin.getConfig().getString("locale");
        regeneration_period = plugin.getConfig().getInt("regeneration-period");
        regeneration_delay = plugin.getConfig().getInt("regeneration-delay");
        disable_hunger = plugin.getConfig().getBoolean("disable-hunger");
    }
    public static void reloadConfig() {
        Bukkit.getLogger().info("[RegenManager] Reloading config...");
        plugin.reloadConfig();
        ConfigManager.loadConfig();
        LanguageManager.loadLangs(plugin);
    }
    public void replaceConfig() {
        String version = plugin.getConfig().getString("version");
        if (!(Objects.requireNonNull(version).equals(plugin.getDescription().getVersion()))) {
            String languagesFolder = (plugin.getDataFolder()).toString();
            Path source = Paths.get(languagesFolder + "/config.yml");
            try {
                Files.move(source, source.resolveSibling("config.yml.old"), REPLACE_EXISTING);
                plugin.saveDefaultConfig();
                Bukkit.getLogger().info("[RegenManager] Your configuration file belong to a different version of RegenManager. Replacing...");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
