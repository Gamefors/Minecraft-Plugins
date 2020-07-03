package com.gmf.lobby.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {
    @EventHandler
    public void playerMoveEvent(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if(p.getLocation().getBlockY() < 0){
            p.teleport(new Location(p.getWorld(),0.5, 5,0.5));
        }
    }
}
