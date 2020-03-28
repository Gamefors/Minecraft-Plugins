package com.gmf.gmfnetwork.main;

import com.gmf.gmfnetwork.commands.LobbyCommand;
import com.gmf.gmfnetwork.commands.PingCommand;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class Main extends Plugin {

    public static String prefix = "§9[GMF]§f";

    @Override
    public void onEnable() {
        registerCommands();
    }

    private void registerCommands() {
        PluginManager pluginManager = ProxyServer.getInstance().getPluginManager();
        pluginManager.registerCommand(this, new LobbyCommand(this));
        pluginManager.registerCommand(this, new PingCommand());

    }

}
