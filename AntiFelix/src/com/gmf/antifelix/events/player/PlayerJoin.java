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

import java.util.UUID;

public class PlayerJoin implements Listener {

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e){
        if(e.getPlayer().getUniqueId().equals(UUID.fromString("38dbea27-b753-49d7-8b5a-a97cce920f4e"))){
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage("§c[AntiFelix] Achtung pepega connected.");
            }
        }

    }

}
