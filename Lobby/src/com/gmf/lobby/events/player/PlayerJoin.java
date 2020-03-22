package com.gmf.lobby.events.player;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerJoin implements Listener {

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e){
        Player p = e.getPlayer();
        p.getInventory().clear();
        Inventory pInv = p.getInventory();
        ItemStack compass = new ItemStack(Material.COMPASS);
        pInv.setItem(0,compass);
    }

}
