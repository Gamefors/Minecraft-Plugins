package com.gmf.lobby.events;

import com.gmf.lobby.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;

public class PlayerJoin implements Listener {

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e){
        Player p = e.getPlayer();
        e.setJoinMessage("§a>>>§f " + p.getName());
        p.teleport(new Location(Bukkit.getWorld("world"), 0.5, 10, 0.5));
        Inventory pInv = p.getInventory();
        pInv.setItem(0, Main.itemList.get(0));

    }

}