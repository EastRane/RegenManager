package me.eastrane.methods;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import me.eastrane.RegenManager;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static me.eastrane.utilities.ConfigManager.getRegeneration_delay;
import static me.eastrane.utilities.ConfigManager.getRegeneration_period;

public class RegainHealthMethod {
    private static final RegenManager plugin = RegenManager.pluginGet();
    private final Set<Player> regeneratingPlayers = new HashSet<>();

    // Check if player already has regeneration
    public void startRegainHealth(Player player) {
        if (!regeneratingPlayers.contains(player)) {
            regeneratingPlayers.add(player);
            scheduleRegainHealth(player);
        }
    }

    // If he does not, regenerate till the end
    private void scheduleRegainHealth(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (player.isOnline() && !player.isDead()) {
                    double currentHealth = player.getHealth();
                    double maxHealth = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getDefaultValue();
                    if (currentHealth < maxHealth) {
                        double newHealth = Math.min(currentHealth + 1, maxHealth);
                        player.setHealth(newHealth);
                    }
                    else {
                        regeneratingPlayers.remove(player);
                        this.cancel();
                    }
                } else {
                    regeneratingPlayers.remove(player);
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, getRegeneration_delay(), getRegeneration_period());
    }

}
