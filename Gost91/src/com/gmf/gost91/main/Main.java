package com.gmf.gost91.main;
import com.gmf.gost91.commands.*;
import com.gmf.gost91.events.player.PlayerInteract;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static String pluginPrefix = "§9[GMF]§f";

    @Override
    public void onEnable() {
        super.onEnable();
        registerCommands();
        registerEvents();
        registerOutGoingPluginChannels();
    }

    private void registerEvents() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerInteract(), this);
    }

    private void registerCommands() {
        getCommand("lobby").setExecutor(new LobbyCommand(this));
        getCommand("pvp").setExecutor(new PvPCommand(getServer().getWorld("world"), getServer().getWorld("world_nether"), getServer().getWorld("world_the_end")));
        getCommand("nick").setExecutor(new NickCommand());
    }

    private void registerOutGoingPluginChannels() {
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

}
