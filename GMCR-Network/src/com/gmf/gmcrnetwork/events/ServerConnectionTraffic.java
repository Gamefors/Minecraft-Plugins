package com.gmf.gmcrnetwork.events;

import com.gmf.gmcrnetwork.main.Main;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.UUID;


public class ServerConnectionTraffic implements Listener {

    @EventHandler
    public void serverConnectedEvent(ServerConnectedEvent e){
        if(ProxyServer.getInstance().getPlayers().contains(ProxyServer.getInstance().getPlayer(UUID.fromString("dfc5d199-b370-40d7-ba45-d675a1055ebe")))){
            if(!e.getPlayer().getUniqueId().equals(UUID.fromString("dfc5d199-b370-40d7-ba45-d675a1055ebe"))){
                ProxiedPlayer gamefors = ProxyServer.getInstance().getPlayer(UUID.fromString("dfc5d199-b370-40d7-ba45-d675a1055ebe"));
                gamefors.sendMessage(new TextComponent(Main.prefix + " " + e.getPlayer().getName() + " §aconnected §fto the §5" + e.getServer().getInfo().getName() + " §fserver."));
            }
        }

    }

}
