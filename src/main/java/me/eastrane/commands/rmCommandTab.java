package me.eastrane.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class rmCommandTab implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("regenmanager")) {
            List<String> completions = new ArrayList<>();

            if (args.length == 1) {
                String[] subArray = {"period", "delay", "hunger", "reload"};
                for (String option : subArray) {
                    if (option.startsWith(args[0].toLowerCase())) {
                        completions.add(option);
                    }
                }
            } else if (args.length == 2) {
                String subCommand = args[0].toLowerCase();
                if (Arrays.asList("period", "delay").contains(subCommand)) {
                    String[] subArray = {"20", "40", "60", "80"};
                    for (String option : subArray) {
                        if (option.startsWith(args[1].toLowerCase())) {
                            completions.add(option);
                        }
                    }
                } else if ("hunger".equals(subCommand)) {
                    String[] subArray = {"disable", "enable"};
                    for (String option : subArray) {
                        if (option.startsWith(args[1].toLowerCase())) {
                            completions.add(option);
                        }
                    }
                }
            }

            return completions.isEmpty() ? null : completions;
        }

        return null;
    }
}
