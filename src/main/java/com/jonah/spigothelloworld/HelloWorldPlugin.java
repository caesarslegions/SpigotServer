package com.jonah.spigothelloworld;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.Random;

public class HelloWorldPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Hello, World! Plugin has been enabled.");
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

        return false;
    }
}
