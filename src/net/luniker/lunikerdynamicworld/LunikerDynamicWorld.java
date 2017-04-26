package net.luniker.lunikerdynamicworld;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import net.luniker.lunikerdynamicworld.Commands.CommandModule;
import net.luniker.lunikerdynamicworld.Commands.DynamicsCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;


public class LunikerDynamicWorld extends JavaPlugin {
    public static LunikerDynamicWorld instance;
    public static WorldEditPlugin worldEdit;

    public static HashMap<String, CommandModule> commands;
    public static HashMap<Player, DynamicsFactory> dynamicsFactories;

    public void onEnable() {
        instance = this;

        worldEdit = (WorldEditPlugin) getServer().getPluginManager().getPlugin("WorldEdit");
        if(worldEdit == null) {
            System.out.println("Error: WorldEdit not found! Make sure the WorldEdit jar file is in your plugins " +
                    "folder! This plugin does not run without WorldEdit.");
        }

        commands = new HashMap<String, CommandModule>();
        dynamicsFactories = new HashMap<Player, DynamicsFactory>();

        registerCommands();
    }

    public void onDisable() {
        instance = null;
        worldEdit = null;
        commands.clear();
        dynamicsFactories.clear();
    }

    public void registerCommands() {
        new DynamicsCommand();
    }
}
