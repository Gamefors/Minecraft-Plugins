package com.gmf.lobby.events.player;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PlayerInteract implements Listener {

    @EventHandler
    public void playerInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if(e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
            e.setCancelled(true);
            return;
        }

        ItemStack selectedItem = p.getInventory().getItemInMainHand();
        if(selectedItem.getType() == Material.COMPASS){

            ItemStack gost91Send = new ItemStack(Material.GRASS);
            ItemMeta gost91SendItemMeta = gost91Send.getItemMeta();

            List<String> gost91SendLore = new ArrayList<String>();
            gost91SendLore.add("Sends you to the Gost91 world.");
            gost91SendItemMeta.setLore(gost91SendLore);


            gost91SendItemMeta.setDisplayName("Gost91");
            gost91Send.setItemMeta(gost91SendItemMeta);


            ItemStack creativeSend = new ItemStack(Material.DIAMOND);
            ItemMeta creativeSendItemMeta = creativeSend.getItemMeta();

            List<String> creativeSendLore = new ArrayList<String>();
            creativeSendLore.add("Sends you to the creative world.");
            creativeSendItemMeta.setLore(creativeSendLore);


            creativeSendItemMeta.setDisplayName("Creative");
            creativeSend.setItemMeta(creativeSendItemMeta);


            Inventory lobbyInventory = Bukkit.createInventory(null, 9, "Lobby");

            lobbyInventory.setItem(0, gost91Send);
            lobbyInventory.setItem(1, creativeSend);

            p.openInventory(lobbyInventory);

        }else{
            e.setCancelled(true);
        }
    }
}
