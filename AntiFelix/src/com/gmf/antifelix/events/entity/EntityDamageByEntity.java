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
            if(e.getDamager().getUniqueId().equals(UUID.fromString("24c7dded-a0f3-43ca-92f0-5ff46451daf3"))){
                e.getDamager().sendMessage("§c[AntiFelix] §4Darfst du nich du pepega!");
                e.setCancelled(true);
            }
        }
    }
}
