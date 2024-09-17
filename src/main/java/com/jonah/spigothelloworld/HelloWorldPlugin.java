package com.jonah.spigothelloworld;

import com.jonah.spigothelloworld.commands.CommandManager;
import com.jonah.spigothelloworld.listeners.PlayerDamageListener;
import com.jonah.spigothelloworld.listeners.EntityDamageListener;
import com.jonah.spigothelloworld.listeners.PlayerInteractListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class HelloWorldPlugin extends JavaPlugin {

    private static HelloWorldPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Hello, World! Plugin has been enabled.");

        // Register commands via CommandManager
        CommandManager commandManager = new CommandManager();
        commandManager.registerCommands();

        // Register event listeners
        Bukkit.getPluginManager().registerEvents(new PlayerDamageListener(), this);  // Archer's Bane
        Bukkit.getPluginManager().registerEvents(new EntityDamageListener(), this);  // Frostbourne
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);  // Register PlayerInteractListener

    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin has been disabled.");
    }

    public static HelloWorldPlugin getInstance() {
        return instance;
    }
}
