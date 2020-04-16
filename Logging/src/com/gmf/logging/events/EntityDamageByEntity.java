package com.gmf.logging.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntity implements Listener {
    
    @EventHandler
    public void entityDamageByEntityEvent(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player){
            Player damager = (Player) e.getDamager();
            Bukkit.getLogger().info(damager.getName() + " damaged entity " + e.getEntity().getName());
        }

    }

}
