package com.gmf.skyblock.events.player;

import com.gmf.skyblock.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void playerQuitEvent(PlayerQuitEvent e){
        Player p = e.getPlayer();
        Main.visitingPlayers.removeIf(players -> players.get(p) != null);
    }

}
