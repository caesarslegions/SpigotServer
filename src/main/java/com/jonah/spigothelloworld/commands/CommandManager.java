package com.jonah.spigothelloworld.commands;

import com.jonah.spigothelloworld.HelloWorldPlugin;

public class CommandManager {

    public void registerCommands() {
        HelloWorldPlugin plugin = HelloWorldPlugin.getInstance();
        plugin.getCommand("tendies").setExecutor(new TendiesCommand());
        plugin.getCommand("equip").setExecutor(new EquipCommand());  // Single EquipCommand to handle all equips
    }
}
