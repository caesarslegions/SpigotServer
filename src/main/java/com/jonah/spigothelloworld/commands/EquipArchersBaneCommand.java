package com.jonah.spigothelloworld.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.enchantments.Enchantment;


import java.util.ArrayList;
import java.util.UUID;

public class EquipArchersBaneCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && args.length > 0 && args[0].equalsIgnoreCase("archersbane")) {
            Player player = (Player) sender;
            giveArchersBane(player);
            player.sendMessage("You've been equipped with Archer's Bane!");
            return true;
        }

        return false;
    }

    private void giveArchersBane(Player player) {
            // Create the chainmail chestplate item
            ItemStack archersBane = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
            ItemMeta meta = archersBane.getItemMeta();

            // Set custom display name
            meta.setDisplayName("Archer's Bane");

            // Add glowing effect (enchantment)
            meta.addEnchant(Enchantment.UNBREAKING, 1, true);

            // Hide the enchantment description in the tooltip
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            // Add lore to make it stand out
            ArrayList<String> lore = new ArrayList<>();
            lore.add("§6Legendary chestplate imbued");
            lore.add("§6with the power to protect against");
            lore.add("§6arrows, but at a price...");
            meta.setLore(lore);

            // Apply meta to the item
            archersBane.setItemMeta(meta);

            // Drop the item above the player
            Location location = player.getLocation().add(0, 1, 0);
            player.getWorld().dropItem(location, archersBane);
        }

}
