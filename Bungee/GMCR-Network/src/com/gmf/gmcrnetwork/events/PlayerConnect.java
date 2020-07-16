package com.gmf.gmcrnetwork.events;

import com.gmf.gmcrnetwork.main.Main;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerConnect implements Listener {

    @EventHandler
    public void PlayerConnectEvent(ServerConnectedEvent e){
        Main.ServerStatus.forEach((s, aBoolean) -> Main.sendServerStatus(e.getPlayer(), e.getServer(), s, String.valueOf(aBoolean)));
    }
}
