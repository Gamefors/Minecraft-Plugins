package com.gmf.skyblock.events.player;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.meta.ItemMeta;


public class PlayerDropItem implements Listener {

    @EventHandler
    public void playerDropItemEvent(PlayerDropItemEvent e){



        if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals("§6Skyblock Menu")){
            e.setCancelled(true);
        }

    }

}
