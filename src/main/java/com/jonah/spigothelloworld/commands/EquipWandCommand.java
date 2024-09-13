package com.jonah.spigothelloworld.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EquipWandCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && args.length > 0 && args[0].equalsIgnoreCase("wand")) {
            Player player = (Player) sender;
            giveMagicMissile(player);
            player.sendMessage("You've been equipped with the Magic Missile!");
            return true;
        }

        return false;
    }

    private void giveMagicMissile(Player player) {
        ItemStack magicMissile = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = magicMissile.getItemMeta();
        meta.setDisplayName("Magic Missile");
        magicMissile.setItemMeta(meta);

        Location location = player.getLocation().add(0, 1, 0);
        player.getWorld().dropItem(location, magicMissile);
    }
}
