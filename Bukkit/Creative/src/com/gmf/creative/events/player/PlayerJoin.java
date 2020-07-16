package com.gmf.creative.events.player;

import com.gmf.creative.main.Main;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e){
        e.getPlayer().setGameMode(GameMode.CREATIVE);
        e.getPlayer().sendMessage(Main.pluginPrefix + " Yor gamemode is now creative.");
    }

}
