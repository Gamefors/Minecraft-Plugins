package com.gmf.cmd.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;




public class PlayerDeath implements Listener {

    public PlayerDeath(){

    }

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent e) {
        e.setDeathMessage(e.getEntity().getDisplayName() + " ist Lost und Schlecht");
        Location deathPos = e.getEntity().getLocation();
        e.getEntity().setHealth(20);
        e.getEntity().teleport(deathPos);
        e.getEntity().setGameMode(GameMode.SPECTATOR);
        for(Player player : Bukkit.getServer().getOnlinePlayers())
        {
            player.setGameMode(GameMode.SPECTATOR);
            player.teleport(deathPos);
        }

    }

}
