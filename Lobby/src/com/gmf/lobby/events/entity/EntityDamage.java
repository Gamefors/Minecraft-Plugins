package com.gmf.lobby.events.entity;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener {

    @EventHandler
    public void entityDamageEvent(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            e.getEntity().sendMessage("§cDarfst du nich du pepega!");
            e.setCancelled(true);
        }
    }

}
