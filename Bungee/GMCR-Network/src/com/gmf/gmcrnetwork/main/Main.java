package com.gmf.gmcrnetwork.main;

import com.gmf.gmcrnetwork.commands.*;
import com.gmf.gmcrnetwork.events.PlayerConnected;
import com.gmf.gmcrnetwork.events.PlayerKick;
import com.gmf.gmcrnetwork.events.PlayerTryConnect;
import com.gmf.gmcrnetwork.objects.ServerInfo;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main extends Plugin {

    private static final String serversDirectory = "/home/minecraft/Inf/Server";
    private static final long updateInterval = 15L;

    public static List<ServerInfo> serverList = new ArrayList<>();
    public static List<String> removedServers = new ArrayList<>();

    public int getServerPort(String serverName){
        File serverProperties = new File(serversDirectory + "/" + serverName + "/server.properties");
        try {
            BufferedReader br = new BufferedReader(new FileReader(serverProperties));
            String[] serverPropertiesString = br.lines().collect(Collectors.joining()).split("server-port=");
            return Integer.parseInt(serverPropertiesString[1].substring(0,5));
        } catch (IOException e) {
            getLogger().warning(serverName + " has no server.properties to get port from therefore has not been added to sub servers.");
        }
        return 0;
    }

    public String[] getServerNames(){
        File file = new File(serversDirectory);
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        for(int i = 0; i< Objects.requireNonNull(directories).length/2; i++){
            String temp = directories[i];
            directories[i] = directories[directories.length -i -1];
            directories[directories.length -i -1] = temp;
        }
        return  directories;
    }

    @Override
    public void onEnable() {
        getProxy().registerChannel("my:lobby");
        String[] serverNames = getServerNames();

        for (String serverName : serverNames) {
            int serverPort = getServerPort(serverName);
            if(serverPort != 0){
                ServerInfo serverInfo = new ServerInfo(serverName, serverPort, false);
                addServer(serverInfo);
                serverList.add(serverInfo);
            }
        }

        registerEvents();
        registerClasses();

        getProxy().getScheduler().schedule(this, this::updateServerStatus, updateInterval, updateInterval, TimeUnit.SECONDS);
    }

    public void updateServerStatus(){
        String[] serverNames = getServerNames();
        for (String serverName : serverNames) {
            if(!ProxyServer.getInstance().getServers().containsKey(serverName)){
                if(!removedServers.contains(serverName)){
                    int serverPort = getServerPort(serverName);
                    if(serverPort == 0) return;
                    ServerInfo serverInfo = new ServerInfo(serverName, serverPort, false);
                    addServer(serverInfo);
                    serverList.add(serverInfo);
                }
            }
        }
        ProxyServer.getInstance().getServers().forEach((serverName, serverInfo) -> {
            serverList.forEach(server -> {
                if(server.name.equals(serverName)) server.status = isReachable((InetSocketAddress)serverInfo.getSocketAddress());
            });
        });

        serverList.forEach(serverInfo -> {
            getLogger().info("Server: " + serverInfo.name + ":" + serverInfo.port + " Status: " + serverInfo.status);
        });

        getProxy().getPlayers().forEach(player -> {
            Main.serverList.forEach(serverInfo ->{
                Main.sendServerStatus(player.getServer(), serverInfo);
            });
        });

    }

    private void registerEvents() {
        getProxy().getPluginManager().registerListener(this, new PlayerConnected());
        getProxy().getPluginManager().registerListener(this, new PlayerTryConnect());
        getProxy().getPluginManager().registerListener(this, new PlayerKick(this));
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
        pluginManager.registerCommand(this, new addServerCommand());
        pluginManager.registerCommand(this, new removeServerCommand());
        pluginManager.registerCommand(this, new PortBindingsCommand());
    }


    public static void addServer(ServerInfo serverInfo) {
        ProxyServer.getInstance().getServers().put(serverInfo.name, ProxyServer.getInstance().constructServerInfo(serverInfo.name, new InetSocketAddress((serverInfo.port)), "GMCR-Network Sub-Server", false));
    }

    public static void removeServer(String name, Server pServer) {
        ProxyServer.getInstance().getServers().remove(name);
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("removeServer");
        out.writeUTF(name);
        pServer.getInfo().sendData( "my:lobby", out.toByteArray() );
    }

    public static void sendServerStatus(Server pServer, ServerInfo serverInfo)
    {
        if(serverInfo.port != 25561){
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("serverstatus");
            out.writeUTF(serverInfo.name);
            out.writeUTF(String.valueOf(serverInfo.status));
            pServer.getInfo().sendData( "my:lobby", out.toByteArray() );
        }
    }
}
