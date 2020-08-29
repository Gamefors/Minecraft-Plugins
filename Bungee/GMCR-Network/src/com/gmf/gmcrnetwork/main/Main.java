package com.gmf.gmcrnetwork.main;

import com.gmf.gmcrnetwork.commands.*;
import com.gmf.gmcrnetwork.events.PlayerConnected;
import com.gmf.gmcrnetwork.events.PlayerTryConnect;
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

    public static Map<String, Boolean> statusOfServers = new HashMap<>();
    public static List<String> removedServers = new ArrayList<>();
    public static List<String> addedServers = new ArrayList<>();
    private static String serverDirectory = "/home/minecraft/GMCR-Network/Server";
    private static String[] serverNames;
    public static HashMap<String, InetSocketAddress> portBindings = new HashMap<>();
    private static long updateStatusOfServersInterval = 30L; //in seconds.
    public int getServerPort(String serverName){
        File serverProperties = new File(serverDirectory + "/" + serverName + "/server.properties");
        try {
            BufferedReader br = new BufferedReader(new FileReader(serverProperties));
            String[] serverPropertiesString = br.lines().collect(Collectors.joining()).split("server-port=");
            return Integer.parseInt(serverPropertiesString[1].substring(0,5));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String[] getServerNames(){
        File file = new File(serverDirectory);
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
        serverNames = getServerNames();
        for (String name : serverNames) {
            addServer(name, new InetSocketAddress(getServerPort(name)));
            portBindings.put(name, new InetSocketAddress(getServerPort(name)));
            statusOfServers.put(name, false);
        }
        registerEvents();
        registerClasses();
        getProxy().getScheduler().schedule(this, this::updateServerStatus, updateStatusOfServersInterval, updateStatusOfServersInterval, TimeUnit.SECONDS);
    }

    public void updateServerStatus(){
        serverNames = getServerNames();
        for (String name : serverNames) {
            if(!ProxyServer.getInstance().getServers().containsKey(name)){
                if(!removedServers.contains(name)){
                    addServer(name, new InetSocketAddress(getServerPort(name)));
                    statusOfServers.put(name, false);
                }
            }
        }
        ProxyServer.getInstance().getServers().forEach((serverName, serverInfo) -> {
            if(serverName.equals("lobby")){
                serverName = "Lobby";
            }
            statusOfServers.replace(serverName, isReachable((InetSocketAddress) serverInfo.getSocketAddress()));
        });

        //statusOfServers.forEach((s, aBoolean) -> System.out.println("Server: " + s + " Status: " + aBoolean));

        getProxy().getPlayers().forEach(p -> Main.statusOfServers.forEach((serverName, serverStatus) -> Main.sendServerStatus(p.getServer(), serverName, String.valueOf(serverStatus))));
    }

    private void registerEvents() {
        getProxy().getPluginManager().registerListener(this, new PlayerConnected());
        getProxy().getPluginManager().registerListener(this, new PlayerTryConnect());
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


    public static void addServer(String name, InetSocketAddress address) {
        ProxyServer.getInstance().getServers().put(name, ProxyServer.getInstance().constructServerInfo(name, address, "GMCR-Network Sub-Server", false));
    }

    public static void removeServer(String name, Server pServer) {
        ProxyServer.getInstance().getServers().remove(name);
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("removeServer");
        out.writeUTF(name);
        pServer.getInfo().sendData( "my:lobby", out.toByteArray() );
    }

    public static void sendServerStatus(Server pServer, String server, String status)
    {
        if(!server.equals("Lobby")){
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("serverstatus");
            out.writeUTF(server);
            out.writeUTF(status);
            pServer.getInfo().sendData( "my:lobby", out.toByteArray() );
        }
    }
}
