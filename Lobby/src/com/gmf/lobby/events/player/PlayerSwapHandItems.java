package com.gmf.lobby.events.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerSwapHandItems implements Listener {

    @EventHandler
    public void playerSwapHandItemsEvent(PlayerSwapHandItemsEvent e){

        e.setCancelled(true);

    }

}
