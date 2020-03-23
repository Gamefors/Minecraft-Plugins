package com.gmf.oneversionone.events.player;

import com.gmf.oneversionone.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    @EventHandler
    public void playerMoveEvent(PlayerMoveEvent e) {
        if(Main.duelling){
            if(!Main.duelInProgress){
                if(Main.fightingPlayers.get(e.getPlayer()) == "pos1"){
                    Location loc = new Location(Bukkit.getWorld("world"), Main.xOffArenaPos1, Main.yOffArenaPos1, Main.zOffArenaPos1);
                    loc.setYaw(90);
                    loc.setPitch(0);
                    e.getPlayer().teleport(loc);
                }else{
                    Location loc = new Location(Bukkit.getWorld("world"), Main.xOffArenaPos2, Main.yOffArenaPos2, Main.zOffArenaPos2);
                    loc.setYaw(-90);
                    loc.setPitch(0);
                    e.getPlayer().teleport(loc);
                }
            }
        }

    }
}
