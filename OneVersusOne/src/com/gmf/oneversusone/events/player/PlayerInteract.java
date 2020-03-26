package com.gmf.oneversusone.events.player;

import com.gmf.oneversusone.main.Main;
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
        if(Main.duelInProgress){
            if(Main.playersInDuel.getValue() == e.getPlayer() || Main.playersInDuel.getValue() == e.getPlayer()){
                e.setCancelled(true);
            }
        }
    }
}
