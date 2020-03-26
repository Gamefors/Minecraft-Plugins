package com.gmf.antifelix.events.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.UUID;


public class PlayerDropItem implements Listener {

    @EventHandler
    public void playerDropItemEvent(PlayerDropItemEvent e){
        if(e.getPlayer().getUniqueId().equals(UUID.fromString("38dbea27-b753-49d7-8b5a-a97cce920f4e"))){
            e.getPlayer().sendMessage("§cDarfst du nich du pepega!");
            e.setCancelled(true);
        }

    }

}
