package com.gamefors.gmf.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class eventPlayerJoin implements Listener {

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e){
        Player p = e.getPlayer();
        Inventory pInv = p.getInventory();

        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta compassItemMeta = compass.getItemMeta();
        compassItemMeta.setDisplayName("Menu");
        List<String> compassLore = new ArrayList<String>();
        compassLore.add("lobby");
        compassItemMeta.setLore(compassLore);
        compass.setItemMeta(compassItemMeta);

        pInv.setItem(0,compass);

    }

}
