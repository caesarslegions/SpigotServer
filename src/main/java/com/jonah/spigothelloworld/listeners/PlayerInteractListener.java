package com.jonah.spigothelloworld.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        // Check if the player is holding the Magic Missile
        if (item != null && item.getType() == Material.BLAZE_ROD && item.getItemMeta() != null && "Magic Missile".equals(item.getItemMeta().getDisplayName())) {

            // Launch fireball
            World world = player.getWorld();
            Location eyeLocation = player.getEyeLocation();
            Vector direction = eyeLocation.getDirection().normalize();
            Fireball fireball = world.spawn(eyeLocation.add(direction.multiply(1.5)), Fireball.class);
            fireball.setDirection(direction);
            fireball.setIsIncendiary(true);
            fireball.setYield(4.0f);
        }
    }
}
