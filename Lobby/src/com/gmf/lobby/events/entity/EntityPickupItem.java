package com.gmf.lobby.events.entity;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class EntityPickupItem implements Listener {

    @EventHandler
    public void entityPickupItemEvent(EntityPickupItemEvent e){
        e.setCancelled(true);
    }

}
