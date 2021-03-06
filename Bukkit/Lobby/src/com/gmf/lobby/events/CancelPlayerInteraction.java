package com.gmf.lobby.events;

import com.gmf.lobby.commands.BuildCommand;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class CancelPlayerInteraction implements Listener {

    @EventHandler
    public void playerSwapHandItemsEvent(PlayerSwapHandItemsEvent e){
        if(!BuildCommand.builders.contains(e.getPlayer().getName())) e.setCancelled(true);
    }

    @EventHandler
    public void playerDropItemEvent(PlayerDropItemEvent e){
        if(!BuildCommand.builders.contains(e.getPlayer().getName())) e.setCancelled(true);
    }

    @EventHandler
    public void entityDamageEvent(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            if(!BuildCommand.builders.contains(e.getEntity().getName())) e.setCancelled(true);
        }
    }

    @EventHandler
    public void entityPickupItemEvent(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player) if (!BuildCommand.builders.contains(e.getEntity().getName())) e.setCancelled(true);
    }

    @EventHandler
    public void playerMoveEvent(PlayerMoveEvent e) {
        if (!BuildCommand.builders.contains(e.getPlayer().getName())) if(e.getPlayer().getLocation().getBlockY() < 0) e.getPlayer().teleport(new Location(e.getPlayer().getWorld(), 0.5,1 ,0.5));
    }

}
