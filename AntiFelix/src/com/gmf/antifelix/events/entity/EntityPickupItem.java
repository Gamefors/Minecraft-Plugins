package com.gmf.antifelix.events.entity;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

import java.util.UUID;

public class EntityPickupItem implements Listener {

    @EventHandler
    public void entityPickupItemEvent(EntityPickupItemEvent e){
        if(e.getEntity() instanceof Player){
            if(((Player) e.getEntity()).getUniqueId().equals(UUID.fromString("24c7dded-a0f3-43ca-92f0-5ff46451daf3"))){
                e.getEntity().sendMessage("§c[AntiFelix] §4Darfst du nich du pepega!");
                e.setCancelled(true);
            }
        }
    }

}
