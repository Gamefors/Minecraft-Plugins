package com.gmf.gost91.main;
import com.gmf.gost91.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static String pluginPrefix = "§9[GMF]§f";

    @Override
    public void onEnable() {
        super.onEnable();
        registerCommands();
        registerOutGoingPluginChannels();
    }

    private void registerCommands() {
        getCommand("lobby").setExecutor(new LobbyCommand(this));
        getCommand("l").setExecutor(new LobbyCommand(this));
        getCommand("hub").setExecutor(new LobbyCommand(this));
        getCommand("pvp").setExecutor(new PvPCommand(getServer().getWorld("world")));
        getCommand("nick").setExecutor(new NickCommand());
    }

    private void registerOutGoingPluginChannels() {
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

}
