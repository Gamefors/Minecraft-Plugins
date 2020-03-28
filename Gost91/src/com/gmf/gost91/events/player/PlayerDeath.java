package com.gmf.gost91.events.player;

import com.gmf.gost91.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public class PlayerDeath implements Listener {

    public static List<ItemStack> savedItemList = null;

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = e.getEntity();
            p.sendMessage(Main.pluginPrefix + " Your died Coordinates:");
            p.sendMessage(Main.pluginPrefix + " §cx: " + p.getLocation().getX());
            p.sendMessage(Main.pluginPrefix + " §2y: " + p.getLocation().getY());
            p.sendMessage(Main.pluginPrefix + " §bz: " + p.getLocation().getZ());

            if(p.getUniqueId().equals(UUID.fromString("dfc5d199-b370-40d7-ba45-d675a1055ebe"))){
                savedItemList = e.getDrops();
            }
        }
    }
}
