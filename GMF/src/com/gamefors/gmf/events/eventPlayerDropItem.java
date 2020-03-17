package com.gamefors.gmf.events;

import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class eventPlayerDropItem implements Listener {

    @EventHandler
    public void playerDropItemEvent(PlayerDropItemEvent e){
        Item droppedItem = e.getItemDrop();
        ItemStack droppedItemStack = droppedItem.getItemStack();
        ItemMeta droppedItemStackMetaData = droppedItemStack.getItemMeta();
        List<String> compassLore = droppedItemStackMetaData.getLore();
        if(compassLore.contains("lobby")) e.setCancelled(true);
    }

}
