package com.jonah.spigothelloworld.listeners;

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
                }

                // Double melee damage
                else if (event.getDamager() instanceof Player || event.getDamager() instanceof org.bukkit.entity.Monster) {
                    event.setDamage(event.getDamage() * 2);
                }
            }
        }
    }
}
