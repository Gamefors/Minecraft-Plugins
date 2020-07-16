package com.gmf.gmcrnetwork.main;

import com.gmf.gmcrnetwork.commands.LobbyCommand;
import com.gmf.gmcrnetwork.commands.PingCommand;
import com.gmf.gmcrnetwork.events.PlayerConnect;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.scheduler.BungeeScheduler;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class Main extends Plugin {

    public static String prefix = "§6[GMCR-Network]§f";

    public static Map<String, Boolean> ServerStatus = new ConcurrentHashMap<String, Boolean>();

    public int port = 25561;

    @Override
    public void onEnable() {

        getProxy().registerChannel("my:lobby");

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

        for (String name : directories) {
            addServer(name, new InetSocketAddress(port));
            ServerInfo server = getProxy().getServerInfo(name);
            if(server == null){
                ServerStatus.put(name, false);
            }else{
                ServerStatus.put(name, true);
            }
            port++;
        }

        getProxy().getScheduler().schedule(this, this::updateServerStatus, 30L, 30L, TimeUnit.SECONDS);

        registerEvents();
        registerClasses();
    }

    public void updateServerStatus(){


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

        for (String name : directories) {
            if(!ProxyServer.getInstance().getServers().containsKey(name)){
                System.out.println("added new server with name: " + name + " and port: " + port + "                        !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                addServer(name, new InetSocketAddress(port));
                ServerStatus.put(name, false);
                port++;
            }
        }

        ProxyServer.getInstance().getServers().forEach((s, serverInfo) -> {
            if(isReachable((InetSocketAddress) serverInfo.getSocketAddress())){
                ServerStatus.replace(s, true);
            }else{
                ServerStatus.replace(s, false);
            }
        });
        ServerStatus.forEach((s, aBoolean) -> {
            System.out.println("Server: " + s + " Status: " + aBoolean);
        });
        getProxy().getPlayers().forEach(p -> {
            Main.ServerStatus.forEach((s, aBoolean) -> Main.sendServerStatus(p, p.getServer(), s, String.valueOf(aBoolean)));
        });

    }

    private void registerEvents() {
        getProxy().getPluginManager().registerListener(this, new PlayerConnect());
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


    public static void sendServerStatus(ProxiedPlayer player, Server pServer, String server, String status)
    {
        Collection<ProxiedPlayer> networkPlayers = ProxyServer.getInstance().getPlayers();
        if ( networkPlayers == null || networkPlayers.isEmpty() )
        {
            return;
        }
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("serverstatus");
        out.writeUTF(server);
        out.writeUTF(status);
        pServer.getInfo().sendData( "my:lobby", out.toByteArray() );
    }


}
