package com.gmf.gmcrnetwork.main;

import com.gmf.gmcrnetwork.commands.LobbyCommand;
import com.gmf.gmcrnetwork.commands.PingCommand;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import java.io.File;
import java.io.FilenameFilter;
import java.net.InetSocketAddress;

public class Main extends Plugin {

    public static String prefix = "§6[GMCR-Network]§f";

    @Override
    public void onEnable() {
        File file = new File("/home/minecraft/GMCR-Network/Server");
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        for(int i=0; i<directories.length/2; i++){
            String temp = directories[i];
            directories[i] = directories[directories.length -i -1];
            directories[directories.length -i -1] = temp;
        }

        int port = 25561;
        for (String name : directories) {
            addServer(name, new InetSocketAddress(port));
            port++;
        }
        registerClasses();
    }

    private void registerClasses() {
        PluginManager pluginManager = ProxyServer.getInstance().getPluginManager();

        pluginManager.registerCommand(this, new LobbyCommand(this));
        pluginManager.registerCommand(this, new PingCommand());
    }


    public static void addServer(String name, InetSocketAddress address) {
        ProxyServer.getInstance().getServers().put(name, ProxyServer.getInstance().constructServerInfo(name, address, "GMCR-Network Sub-Server", false));
    }
    public static void removeServerByName(String name) {
        ProxyServer.getInstance().getServers().remove(name);
    }

}
