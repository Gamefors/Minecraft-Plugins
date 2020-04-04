package com.gmf.antifelix.events.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.UUID;


public class PlayerDropItem implements Listener {

    @EventHandler
    public void playerDropItemEvent(PlayerDropItemEvent e){
        if(e.getPlayer().getUniqueId().equals(UUID.fromString("24c7dded-a0f3-43ca-92f0-5ff46451daf3"))){
            e.getPlayer().sendMessage("§c[AntiFelix] §4Darfst du nich du pepega!");
            e.setCancelled(true);
        }

    }

}
