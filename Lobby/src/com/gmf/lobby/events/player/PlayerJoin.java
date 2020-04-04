package com.gmf.lobby.events.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerJoin implements Listener {

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e){
        Player p = e.getPlayer();
        p.teleport(new Location(Bukkit.getWorld("world"), 0.5, 10, 0.5));
        p.getInventory().clear();
        Inventory pInv = p.getInventory();
        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta compassMeta = compass.getItemMeta();
        compassMeta.setDisplayName("§6Server selector");
        compass.setItemMeta(compassMeta);
        pInv.setItem(0,compass);
    }

}
