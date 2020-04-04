package com.gmf.skyblock.events.player;

import net.minecraft.server.v1_15_R1.ItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerSwapHandItems implements Listener {

    @EventHandler
    public void playerSwapHandItemsEvent(PlayerSwapHandItemsEvent e){

        if(e.getOffHandItem().hasItemMeta()){

            if(e.getOffHandItem().getItemMeta().getDisplayName().equals("§6Skyblock Menu")){

                e.setCancelled(true);

            }

        }

    }

}
