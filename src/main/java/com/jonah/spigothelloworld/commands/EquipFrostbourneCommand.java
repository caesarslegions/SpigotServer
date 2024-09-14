package com.jonah.spigothelloworld.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.Damageable;

import java.util.ArrayList;

public class EquipFrostbourneCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && args.length > 0 && args[0].equalsIgnoreCase("frostbourne")) {
            Player player = (Player) sender;
            giveFrostbourne(player);
            player.sendMessage("You've been equipped with Frostbourne!");
            return true;
        }

        return false;
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
