package com.jonah.spigothelloworld;

import com.jonah.spigothelloworld.commands.EquipWandCommand;
import com.jonah.spigothelloworld.commands.TendiesCommand;
import com.jonah.spigothelloworld.listeners.PlayerInteractListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class HelloWorldPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Hello, World! Plugin has been enabled.");

        // Register commands
        this.getCommand("tendies").setExecutor(new TendiesCommand());
        this.getCommand("equip").setExecutor(new EquipWandCommand());

        // Register event listeners
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin has been disabled.");
    }
}
