package com.gmf.antifelix.events.entity;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.UUID;

public class EntityDamageByEntity implements Listener {
    @EventHandler
    public void entityDamageByEntityEvent(EntityDamageByEntityEvent e) {
        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            if(e.getDamager().getUniqueId().equals(UUID.fromString("38dbea27-b753-49d7-8b5a-a97cce920f4e"))){
                e.getDamager().sendMessage("§cDarfst du nich du pepega!");
                e.setCancelled(true);
            }
        }
    }
}
