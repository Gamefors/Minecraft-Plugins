package com.gmf.gmcrnetwork.main;

import com.gmf.gmcrnetwork.commands.LobbyCommand;
import com.gmf.gmcrnetwork.commands.PingCommand;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main extends Plugin {

    public static String prefix = "§6[GMCR-Network]§f";

    public static Map<String, Boolean> ServerStatus = new ConcurrentHashMap<String, Boolean>();

    @Override
    public void onEnable() {

        getProxy().registerChannel("serverStatus");



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
            ServerInfo server = getProxy().getServerInfo(name);
            if(server != null){
                ServerStatus.put(name, true);
            }else{
                ServerStatus.put(name, false);
            }
            addServer(name, new InetSocketAddress(port));
            port++;
        }


        registerClasses();
    }

    private boolean isReachable(InetSocketAddress address) {
        Socket socket = new Socket();
        try {
            socket.connect(address, 500);
            socket.close();
            return true;
        } catch(IOException ignored) {
        }
        return false;
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


    public static void sendCustomData(ProxiedPlayer player, String data1)
    {
        Collection<ProxiedPlayer> networkPlayers = ProxyServer.getInstance().getPlayers();

        if ( networkPlayers == null || networkPlayers.isEmpty() )
        {
            return;
        }
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF( "serverStatus" ); // the channel could be whatever you want
        out.writeUTF( data1 ); // this data could be whatever you want

        player.getServer().getInfo().sendData( "serverStatus", out.toByteArray() );
    }

}
