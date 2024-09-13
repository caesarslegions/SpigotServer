package com.jonah.spigothelloworld;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.Random;

public class HelloWorldPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("Hello, World! Plugin has been enabled.");
        // Register the plugin as an event listener
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin has been disabled.");
    }

    // Handle the "/tendies" command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("tendies")) {
            // Check if the sender is a player
            if (sender instanceof Player) {
                Player player = (Player) sender;

                // Get player's location and world
                Location playerLocation = player.getLocation();
                World world = player.getWorld();

                // Add thunder and lightning effects
                world.setStorm(true);  // Start rain (for clouds effect)
                world.strikeLightningEffect(playerLocation);  // Lightning at player's location
                world.setThundering(true);  // Activate thunder

                // Simulate cloud particles
                Random random = new Random();
                for (int i = 0; i < 50; i++) {
                    double offsetX = (random.nextDouble() * 20) - 10; // Random x within -10 to +10 blocks
                    double offsetZ = (random.nextDouble() * 20) - 10; // Random z within -10 to +10 blocks
                    Location particleLocation = playerLocation.clone().add(offsetX, 10, offsetZ);
                    world.spawnParticle(Particle.CLOUD, particleLocation, 1); // Spawn cloud particle
                }

                // Spawn 100 cooked chickens in a 10-block radius around the player
                for (int i = 0; i < 100; i++) {
                    // Generate random x and z within a 10-block radius
                    double offsetX = (random.nextDouble() * 20) - 10;
                    double offsetZ = (random.nextDouble() * 20) - 10;

                    // Get the spawn location 10 blocks above the player
                    Location spawnLocation = playerLocation.clone().add(offsetX, 10, offsetZ);

                    // Drop the cooked chicken from the sky
                    world.dropItem(spawnLocation, new ItemStack(Material.COOKED_CHICKEN));
                }

                // Send a message to the player confirming the rain of tendies
                player.sendMessage("It's raining tendies (Thunder, Lightning, and Clouds included)!");

                return true;
            }
        }

        if (command.getName().equalsIgnoreCase("equip") && args.length > 0 && args[0].equalsIgnoreCase("wand")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                giveMagicMissile(player);
                player.sendMessage("You've been equipped with the Magic Missile!");
                return true;
            }
        }

        return false;
    }

    // Method to give the player the Magic Missile (Blaze Rod)
    private void giveMagicMissile(Player player) {
        // Create the blaze rod item
        ItemStack magicMissile = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = magicMissile.getItemMeta();

        // Set custom display name
        meta.setDisplayName("Magic Missile");

        // Optionally, add lore or other metadata to the item
        magicMissile.setItemMeta(meta);

        // Drop the item above the player
        Location location = player.getLocation().add(0, 1, 0);
        player.getWorld().dropItem(location, magicMissile);
    }

    // Event handling for when the player uses the Magic Missile
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        // Check if the player is holding the Magic Missile (Blaze Rod)
        if (item != null && item.getType() == Material.BLAZE_ROD && item.getItemMeta() != null && "Magic Missile".equals(item.getItemMeta().getDisplayName())) {

            // Spawn a fireball in front of the player
            World world = player.getWorld();
            Location eyeLocation = player.getEyeLocation();
            Vector direction = eyeLocation.getDirection().normalize();

            Fireball fireball = world.spawn(eyeLocation.add(direction.multiply(1.5)), Fireball.class);
            fireball.setDirection(direction);
            fireball.setIsIncendiary(true);  // Make sure the fireball can set blocks on fire
            fireball.setYield(4.0f);  // Explosion size (higher values create larger explosions)
        }
    }
}
