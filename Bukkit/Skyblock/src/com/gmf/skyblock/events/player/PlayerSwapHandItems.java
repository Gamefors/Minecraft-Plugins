package com.gmf.skyblock.events.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerSwapHandItems implements Listener {

    @EventHandler
    public void playerSwapHandItemsEvent(PlayerSwapHandItemsEvent e){

        if(e.getOffHandItem().hasItemMeta()){

            if(e.getOffHandItem().getItemMeta().getDisplayName().equals("§b§6Skyblock Menu §7(Right click)")){

                e.setCancelled(true);

            }

        }

    }

}
