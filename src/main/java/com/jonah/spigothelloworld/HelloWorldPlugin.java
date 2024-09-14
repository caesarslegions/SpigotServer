package com.jonah.spigothelloworld;

import com.jonah.spigothelloworld.commands.EquipFrostbourneCommand;
import com.jonah.spigothelloworld.commands.EquipArchersBaneCommand;
import com.jonah.spigothelloworld.commands.EquipWandCommand;
import com.jonah.spigothelloworld.commands.TendiesCommand;
import com.jonah.spigothelloworld.listeners.PlayerDamageListener;
import com.jonah.spigothelloworld.listeners.EntityDamageListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class HelloWorldPlugin extends JavaPlugin {

    private static HelloWorldPlugin instance;  // Static instance of the plugin

    @Override
    public void onEnable() {
        instance = this;  // Store the plugin instance when the plugin is enabled
        getLogger().info("Hello, World! Plugin has been enabled.");

        // Register commands
        this.getCommand("tendies").setExecutor(new TendiesCommand());
        this.getCommand("equip").setExecutor(new EquipWandCommand());
        this.getCommand("equip").setExecutor(new EquipArchersBaneCommand());
        this.getCommand("equip").setExecutor(new EquipFrostbourneCommand());

        // Register event listeners
        Bukkit.getPluginManager().registerEvents(new PlayerDamageListener(), this);  // Archer's Bane
        Bukkit.getPluginManager().registerEvents(new EntityDamageListener(), this);  // Frostbourne
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin has been disabled.");
    }

    // Static method to get the plugin instance
    public static HelloWorldPlugin getInstance() {
        return instance;
    }
}
