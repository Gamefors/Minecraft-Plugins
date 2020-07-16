package com.gmf.creative.main;

import com.gmf.creative.events.player.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static String pluginPrefix = "[Creative]";

    @Override
    public void onEnable() {
        super.onEnable();
        registerEvents();
        registerCommands();
        registerOutGoingPluginChannels();
        World world = Bukkit.getWorld("world");
        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setPVP(false);
        world.setDifficulty(Difficulty.PEACEFUL);
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerJoin(), this);
    }

    private void registerCommands() {

    }

    private void registerOutGoingPluginChannels() {
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

}
