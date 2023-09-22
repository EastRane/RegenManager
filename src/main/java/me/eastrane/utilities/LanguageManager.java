package me.eastrane.utilities;

import me.eastrane.RegenManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class LanguageManager {
    private static final RegenManager plugin = RegenManager.pluginGet();
    public static String language;
    public static String prefix;
    private static String fileName;

    public LanguageManager() {
        loadLangs(plugin);
        prefix = Colorize(getTranslation("plugin_prefix"));
    }

    public static void loadLangs(RegenManager plugin) {
        LanguageManager.language = ConfigManager.language;
        String[] languages = {"en_US", "uk_UA", "ru_RU"};
        String langExtract;
        String languagesFolder = plugin.getDataFolder() + "/languages";
        boolean isReplaced = false;
        for (String s : languages) {
            langExtract = s + ".yml";
            File languageFile = new File(plugin.getDataFolder() + "/languages", langExtract);
            String version = plugin.getConfig().getString("version");
            if (!languageFile.exists()) {
                plugin.saveResource("languages/" + langExtract, false);
            } else {
                if (version != null && !(version.equals(plugin.getDescription().getVersion()))) {
                    isReplaced = true;
                    Path source = Paths.get(languagesFolder + "/" + langExtract);
                    try {
                        Files.move(source, source.resolveSibling(langExtract + ".old"), REPLACE_EXISTING);
                        plugin.saveResource("languages/" + langExtract, false);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            fileName = language + ".yml";
            YamlConfiguration.loadConfiguration(new File(languagesFolder, fileName));
        }
        prefix = Colorize(getTranslation("plugin_prefix"));
        if (isReplaced) {
            Bukkit.getLogger().info("[RegenManager] Your localization files belong to a different version of RegenManager. Replacing...");
        }
        Bukkit.getLogger().info("[RegenManager] Using locale " + language);
    }

    public static void sendMessage(CommandSender sender, String translationKey, Object... args) {
        String translation = getTranslation(translationKey);
        if (translation != null) {
            String message = String.format(translation, args);
            sender.sendMessage(Colorize(prefix + message));
        } else {
            sender.sendMessage("Translation not found for key: " + translationKey);
        }
    }

    public static String Colorize(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String getTranslation(String messageKey) {
        File languageFile = new File(plugin.getDataFolder(), "languages/" + fileName);
        if (!languageFile.exists()) {
            return "Translation file not found '" + language + "'";
        }
        YamlConfiguration yamlConfig = YamlConfiguration.loadConfiguration(languageFile);
        if (yamlConfig.isSet(messageKey)) {
            return yamlConfig.getString(messageKey);
        }
        return "Translation not found for '" + messageKey + "'";
    }

}