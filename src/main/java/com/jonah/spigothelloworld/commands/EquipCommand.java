package com.jonah.spigothelloworld.commands;

import com.jonah.spigothelloworld.interfaces.PlayerOnlyCommand;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class EquipCommand extends PlayerOnlyCommand {

    @Override
    protected boolean onExecute(Player player, Command command, String label, String[] args) {
        if (args.length == 0) {
            player.sendMessage("Please specify an item to equip: wand, archersbane, or frostbourne.");
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "wand":
                giveMagicMissile(player);
                player.sendMessage("You've been equipped with the Magic Missile!");
                break;
            case "archersbane":
                giveArchersBane(player);
                player.sendMessage("You've been equipped with Archer's Bane!");
                break;
            case "frostbourne":
                giveFrostbourne(player);
                player.sendMessage("You've been equipped with Frostbourne!");
                break;
            default:
                player.sendMessage("Invalid item. Please choose wand, archersbane, or frostbourne.");
                return false;
        }
        return true;
    }

    private void giveMagicMissile(Player player) {
            ItemStack magicMissile = new ItemStack(Material.BLAZE_ROD);
            ItemMeta meta = magicMissile.getItemMeta();
            meta.setDisplayName("Magic Missile");
            magicMissile.setItemMeta(meta);

            Location location = player.getLocation().add(0, 1, 0);
            player.getWorld().dropItem(location, magicMissile);
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

    private void giveFrostbourne(Player player) {
        // Create the diamond sword item
        ItemStack frostbourne = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = frostbourne.getItemMeta();

        // Set custom display name
        meta.setDisplayName("Frostbourne");

        // Add an enchantment to give it a glow
        meta.addEnchant(Enchantment.getByName("DAMAGE_ALL"), 1, true);

        // Add lore to make it stand out
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§bA sword of ice that freezes your enemies");
        lore.add("§bbut renders them invulnerable...");
        meta.setLore(lore);

        // Apply the meta to the item
        frostbourne.setItemMeta(meta);

        // Drop the item above the player
        Location location = player.getLocation().add(0, 1, 0);
        player.getWorld().dropItem(location, frostbourne);
    }
}
