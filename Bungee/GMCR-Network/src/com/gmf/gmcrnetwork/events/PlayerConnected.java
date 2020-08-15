package com.gmf.gmcrnetwork.events;

import com.gmf.gmcrnetwork.main.Main;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerConnected implements Listener {

    @EventHandler
    public void PlayerConnectedEvent(ServerConnectedEvent e){
        Main.statusOfServers.forEach((serverName, serverStatus) -> Main.sendServerStatus(e.getServer(), serverName, String.valueOf(serverStatus)));
    }
}
