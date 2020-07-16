package com.gmf.skyblock.events.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;

public class PlayerDeath implements Listener {

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent e){

        Iterator<ItemStack> drops = e.getDrops().iterator();
        while(drops.hasNext()) {
            ItemStack current = drops.next();
            if (current.hasItemMeta()) {

                if (current.getItemMeta().getDisplayName().equals("§6Skyblock Menu")) {

                    drops.remove();

                }

            }
        }

    }
}
