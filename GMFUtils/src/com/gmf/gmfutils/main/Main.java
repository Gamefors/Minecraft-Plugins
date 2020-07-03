package com.gmf.gmfutils.main;

import com.gmf.gmfutils.commands.LinkCommand;
import com.gmf.gmfutils.commands.PositionCommand;
import com.gmf.gmfutils.events.player.PlayerDeath;
import com.gmf.gmfutils.events.player.EnderChestOptimization;
import com.gmf.gmfutils.events.player.SaveGearOnDeath;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static String prefix = "§9[GMFUtils]§f";

    @Override
    public void onEnable() {
        super.onEnable();
        registerCommands();
        registerEvents();
    }

    private void registerEvents() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new EnderChestOptimization(), this);
        pluginManager.registerEvents(new PlayerDeath(), this);
        pluginManager.registerEvents(new SaveGearOnDeath(this), this);
    }

    private void registerCommands() {
        getCommand("link").setExecutor(new LinkCommand());
        getCommand("position").setExecutor(new PositionCommand());
    }
}
