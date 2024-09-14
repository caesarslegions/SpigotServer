package com.jonah.spigothelloworld.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import com.jonah.spigothelloworld.HelloWorldPlugin;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        // Check if the damager is a player and is using Frostbourne
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            ItemStack weapon = player.getInventory().getItemInMainHand();

            if (weapon != null && weapon.getType() == Material.DIAMOND_SWORD &&
                    weapon.getItemMeta() != null && weapon.getItemMeta().getDisplayName().equals("Frostbourne")) {

                if (event.getEntity() instanceof LivingEntity) {
                    LivingEntity target = (LivingEntity) event.getEntity();

                    // Get the main scoreboard and team for frozen entities
                    ScoreboardManager manager = Bukkit.getScoreboardManager();
                    Scoreboard board = manager.getMainScoreboard();

                    final Team blueTeam;
                    if (board.getTeam("frozen_entities") == null) {
                        blueTeam = board.registerNewTeam("frozen_entities");
                        blueTeam.setColor(ChatColor.BLUE);
                    } else {
                        blueTeam = board.getTeam("frozen_entities");
                    }

                    blueTeam.addEntry(target.getUniqueId().toString());  // Add the target to the blue team
                    target.setGlowing(true);  // Make the target glow blue

                    // Freeze the entity (immobilize it)
                    target.setAI(false);  // Disable movement and AI
                    target.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 10));  // Apply slowness for 5 seconds
                    target.setInvulnerable(true);  // Make invulnerable

                    // Add debug message for start of freeze effect
                    Bukkit.getLogger().info("Entity frozen for 5 seconds: " + target.getName());

                    // Unfreeze the entity after 5 seconds
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            target.setAI(true);  // Restore AI
                            target.setGlowing(false);  // Remove glow
                            target.setInvulnerable(false);  // Remove invulnerability
                            blueTeam.removeEntry(target.getUniqueId().toString());  // Remove from team

                            // Add debug message for end of freeze effect
                            Bukkit.getLogger().info("Entity unfrozen: " + target.getName());
                        }
                    }.runTaskLater(HelloWorldPlugin.getInstance(), 100);  // Use the static plugin instance here
                }
            }
        }
    }
}
