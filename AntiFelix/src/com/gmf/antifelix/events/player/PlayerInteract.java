package com.gmf.antifelix.events.player;

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
import java.util.UUID;

public class PlayerInteract implements Listener {

    @EventHandler
    public void playerInteractEvent(PlayerInteractEvent e) {
        if(e.getPlayer().getUniqueId().equals(UUID.fromString("38dbea27-b753-49d7-8b5a-a97cce920f4e"))){
            e.getPlayer().sendMessage("§cDarfst du nich du pepega!");
            e.setCancelled(true);
        }
    }
}
