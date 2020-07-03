package com.gmf.gmcrnetwork.main;

import com.gmf.gmcrnetwork.commands.LobbyCommand;
import com.gmf.gmcrnetwork.commands.PingCommand;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class Main extends Plugin {

    public static String prefix = "§6[GMCR-Network]§f";

    @Override
    public void onEnable() {
        registerClasses();
    }

    private void registerClasses() {
        PluginManager pluginManager = ProxyServer.getInstance().getPluginManager();

        pluginManager.registerCommand(this, new LobbyCommand(this));
        pluginManager.registerCommand(this, new PingCommand());
    }

}
