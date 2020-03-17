package com.gamefors.gmf.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class eventPlayerInteract implements Listener {

    @EventHandler
    public void playerInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack selectedItem = p.getItemInHand();
        ItemMeta droppedItemStackMetaData = selectedItem.getItemMeta();
        List<String> compassLore = droppedItemStackMetaData.getLore();
        if(compassLore.contains("lobby")){
            Inventory menuInv = Bukkit.createInventory(null, InventoryType.CHEST);
            ItemStack spawn = new ItemStack(Material.REDSTONE_BLOCK);
            p.openInventory(menuInv);
        }
    }
}
