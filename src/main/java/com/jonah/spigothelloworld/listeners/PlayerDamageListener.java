package com.jonah.spigothelloworld.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDamageListener implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            ItemStack chestplate = player.getInventory().getChestplate();

            // Archer's Bane Logic (Projectile Damage Halved, Melee Damage Doubled)
            if (chestplate != null && chestplate.getType() == Material.CHAINMAIL_CHESTPLATE &&
                    chestplate.getItemMeta() != null && chestplate.getItemMeta().getDisplayName().equals("Archer's Bane")) {

                // Halve projectile damage
                if (event.getCause() == EntityDamageByEntityEvent.DamageCause.PROJECTILE) {
                    event.setDamage(event.getDamage() / 2);
                    // Log to server console
                    Bukkit.getLogger().info("Archer's Bane effect triggered: Projectile damage halved for player " + player.getName());
                    // Send a message to the player
                    player.sendMessage("Archer's Bane activated! Projectile damage halved.");
                }

                // Double melee damage
                else if (event.getDamager() instanceof Player || event.getDamager() instanceof org.bukkit.entity.Monster) {
                    event.setDamage(event.getDamage() * 2);
                    // Log to server console
                    Bukkit.getLogger().info("Archer's Bane effect triggered: Melee damage doubled for player " + player.getName());
                    // Send a message to the player
                    player.sendMessage("Archer's Bane activated! Melee damage doubled.");
                }
            }
        }
    }
}
