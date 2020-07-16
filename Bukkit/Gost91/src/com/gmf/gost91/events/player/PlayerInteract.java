package com.gmf.gost91.events.player;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener {

    @EventHandler
    public void playerInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(p.getInventory().getItemInMainHand().getType() == Material.ENDER_CHEST){
            if(p.isSneaking()){
                p.openInventory(p.getEnderChest());
                e.setCancelled(true);
            }
        }
    }
}
