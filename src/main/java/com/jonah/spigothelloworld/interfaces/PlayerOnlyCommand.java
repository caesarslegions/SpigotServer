package com.jonah.spigothelloworld.interfaces;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class PlayerOnlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player to call this command.");
            return true;
        }

        Player player = (Player) sender;
        if (!hasPermission(command, player)) {
            sender.sendMessage(ChatColor.RED + command.getPermissionMessage());
            return true;
        }

        boolean result = onExecute(player, command, label, args);
        if (!result) {
            sender.sendMessage(ChatColor.WHITE + command.getUsage());
            sender.sendMessage(ChatColor.GRAY + command.getDescription());
        }
        return true;
    }

    private boolean hasPermission(Command command, Player player) {
        return player.isOp() || command.getPermission() == null || player.hasPermission(command.getPermission());
    }

    abstract protected boolean onExecute(Player player, Command command, String label, String[] args);
}
