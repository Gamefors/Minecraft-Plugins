package com.gmf.oneversusone.events.player;

import com.gmf.oneversusone.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    @EventHandler
    public void playerMoveEvent(PlayerMoveEvent e) {

        if(e.getPlayer() instanceof Player){
            if(Main.playersInDuel != null){
                if(!Main.duelInProgress){
                    if(Main.playersInDuel.getKey() == e.getPlayer()){
                        e.getPlayer().teleport(Main.arenaPlayerSpawnpoint1);
                    }else if(Main.playersInDuel.getValue() == e.getPlayer()){
                        e.getPlayer().teleport(Main.arenaPlayerSpawnpoint2);
                    }
                }
            }
        }
    }
}



