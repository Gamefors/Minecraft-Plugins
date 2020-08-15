package com.gmf.gmcrnetwork.events;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerTryConnect implements Listener {

    @EventHandler
    public void PlayerConnectEvent(ServerConnectEvent e){
        ProxiedPlayer p = e.getPlayer();
        if((e.getTarget().getName().equals("xr7vbW5D"))){
            if(!p.getDisplayName().equals("Gamefors")){
                e.setCancelled(true);
                p.sendMessage(new TextComponent("§cDu hast keine Berechtigung diesen Server zu betreten."));
            }
        }
    }
}
