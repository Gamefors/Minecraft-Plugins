package com.gmf.antifelix.events.player;

import com.gmf.antifelix.main.Main;
import org.bukkit.Bukkit;
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
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage("§c[AntiFelix] Achtung pepega connected.");
        }
    }

}
