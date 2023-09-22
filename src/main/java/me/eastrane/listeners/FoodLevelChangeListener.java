package me.eastrane.listeners;

import me.eastrane.RegenManager;
import me.eastrane.utilities.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChangeListener implements Listener {

    public FoodLevelChangeListener(RegenManager plugin)
    {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    // If player's food level is trying to change, we prevent it and set food level to maximum
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        boolean disable_hunger = ConfigManager.getDisable_hunger();
        if (disable_hunger) {
            if (event.getEntityType() != EntityType.PLAYER) return;
            Player player = (Player) event.getEntity();
            player.setFoodLevel(20);
            event.setCancelled(true);
        }
    }

}