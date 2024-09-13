package com.jonah.spigothelloworld;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

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

                // Get player's location
                Location playerLocation = player.getLocation();

                // Spawn 30 cooked chickens above the player
                for (int i = 0; i < 30; i++) {
                    // Get the location slightly above the player
                    Location spawnLocation = playerLocation.clone().add(0, 10, 0).setDirection(playerLocation.getDirection());

                    // Drop the cooked chicken
                    player.getWorld().dropItem(spawnLocation, new ItemStack(Material.COOKED_CHICKEN));
                }

                // Send a message to the player confirming the rain of tendies
                player.sendMessage("It's raining tendies!");

                return true;
            } else {
                sender.sendMessage("Only players can use this command!");
                return false;
            }
        }

        return false;
    }
}

