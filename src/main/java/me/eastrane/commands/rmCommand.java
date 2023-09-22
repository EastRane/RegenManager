package me.eastrane.commands;
import me.eastrane.RegenManager;
import me.eastrane.utilities.ConfigManager;
import me.eastrane.utilities.LanguageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static java.lang.Integer.parseInt;

public class rmCommand implements CommandExecutor {
    private static final RegenManager plugin = RegenManager.pluginGet();
    String version = plugin.getDescription().getVersion();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player || sender instanceof ConsoleCommandSender) {
            if (args.length == 0) {
                LanguageManager.sendMessage(sender,"usage", version);
            } else if (sender instanceof Player && sender.hasPermission("regenmanager.admin")){
                if (args[0].equalsIgnoreCase("reload")) {
                    if (args.length == 1) {
                        ConfigManager.reloadConfig();
                        LanguageManager.sendMessage(sender, "config_reloaded");
                    }
                    else {
                        LanguageManager.sendMessage(sender, "too_many_arguments");
                    }
                }
                else if (args[0].equalsIgnoreCase("period")) {
                    if (args.length == 2) {
                        int period;
                        try {
                            period = parseInt(args[1]);
                            plugin.getConfig().set("regeneration-period", period);
                            plugin.saveConfig();
                            LanguageManager.sendMessage(sender, "regeneration_period_set", period);
                        } catch (NumberFormatException e) {
                            LanguageManager.sendMessage(sender,"argument_not_number");
                        }
                    }
                    if (args.length == 1) {
                        LanguageManager.sendMessage(sender, "too_few_arguments");
                    }
                    else if (args.length > 2) {
                        LanguageManager.sendMessage(sender, "too_many_arguments");
                    }
                }
                else if (args[0].equalsIgnoreCase("delay")) {
                    if (args.length == 2) {
                        int delay;
                        try {
                            delay = parseInt(args[1]);
                            plugin.getConfig().set("regeneration-delay", delay);
                            plugin.saveConfig();
                            LanguageManager.sendMessage(sender, "regeneration_delay_set", delay);
                        } catch (NumberFormatException e) {
                            LanguageManager.sendMessage(sender,"argument_not_number");
                        }
                    }
                    else if (args.length == 1) {
                        LanguageManager.sendMessage(sender, "too_few_arguments");
                    }
                    else {
                        LanguageManager.sendMessage(sender, "too_many_arguments");
                    }
                }
                else if (args[0].equalsIgnoreCase("hunger")) {
                    if (args.length == 2) {
                        if (args[1].equalsIgnoreCase("enable")) {
                            plugin.getConfig().set("disable-hunger", false);
                            plugin.saveConfig();
                            LanguageManager.sendMessage(sender, "hunger_enabled");
                        }
                        else if (args[1].equalsIgnoreCase("disable")) {
                            plugin.getConfig().set("disable-hunger", true);
                            plugin.saveConfig();
                            LanguageManager.sendMessage(sender, "hunger_disabled");
                        }
                        else {
                            LanguageManager.sendMessage(sender, "wrong_argument", "disable, enable");
                        }
                    }
                    else if (args.length == 1) {
                        LanguageManager.sendMessage(sender, "too_few_arguments");
                    }
                    else {
                        LanguageManager.sendMessage(sender, "too_many_arguments");
                    }
                }
                else {
                    LanguageManager.sendMessage(sender, "wrong_subcommand", "delay, period, hunger, reload");
                }
            }
        }
        return true;
    }
}
