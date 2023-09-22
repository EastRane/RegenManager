package me.eastrane.listeners;

import me.eastrane.methods.RegainHealthMethod;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import me.eastrane.RegenManager;

import java.util.Objects;

public class PlayerJoinListener implements Listener {

    RegainHealthMethod healthMethod = new RegainHealthMethod();

    public PlayerJoinListener(RegenManager plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    // If player's health isn't full when he joined, we should regenerate it
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        double currentHealth = player.getHealth();
        double maxHealth = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getDefaultValue();
        if (currentHealth < maxHealth) {
            healthMethod.startRegainHealth(player);
        }
    }
}