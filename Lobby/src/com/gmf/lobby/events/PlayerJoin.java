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
        p.teleport(new Location(Bukkit.getWorld("world"), 0.5, 3, 0.5));
        Inventory pInv = p.getInventory();
        pInv.setItem(0, Main.itemList.get(0));
        p.sendMessage("§nNützliche Links:");
        p.sendMessage("");
        p.sendMessage("§d§nOptifine für die 1.16.1 (Mirror Link):");
        p.sendMessage("");
        p.sendMessage("§ahttp://optifine.net/adloadx?f=preview_OptiFine_1.16.1_HD_U_G2_pre1.jar");
        p.sendMessage("");
        p.sendMessage("");
    }

}
