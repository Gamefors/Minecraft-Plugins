package com.gmf.creative.main;

import com.gmf.creative.commands.LobbyCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

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
    }

    private void registerOutGoingPluginChannels() {
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

}
