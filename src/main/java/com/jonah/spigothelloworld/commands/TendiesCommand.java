package com.jonah.spigothelloworld.commands;

import com.jonah.spigothelloworld.interfaces.PlayerOnlyCommand;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class TendiesCommand extends PlayerOnlyCommand {

    @Override
    protected boolean onExecute(Player player, Command command, String label, String[] args) {
        Location playerLocation = player.getLocation();
        World world = player.getWorld();

        // Add thunder and lightning effects
        world.setStorm(true);
        world.strikeLightningEffect(playerLocation);
        world.setThundering(true);

        // Cloud particle effect
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            double offsetX = (random.nextDouble() * 20) - 10;
            double offsetZ = (random.nextDouble() * 20) - 10;
            Location particleLocation = playerLocation.clone().add(offsetX, 10, offsetZ);
            world.spawnParticle(Particle.CLOUD, particleLocation, 1);
        }

        // Drop 100 cooked chickens
        for (int i = 0; i < 100; i++) {
            double offsetX = (random.nextDouble() * 20) - 10;
            double offsetZ = (random.nextDouble() * 20) - 10;
            Location spawnLocation = playerLocation.clone().add(offsetX, 10, offsetZ);
            world.dropItem(spawnLocation, new ItemStack(Material.COOKED_CHICKEN));
        }

        player.sendMessage("It's raining tendies!");
        return true;
    }
}
