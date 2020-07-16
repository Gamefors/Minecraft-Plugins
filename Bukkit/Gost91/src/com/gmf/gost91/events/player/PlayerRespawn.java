package com.gmf.gost91.events.player;

import com.gmf.gost91.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class PlayerRespawn implements Listener {

    Main plugin;

    public PlayerRespawn(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void playerRespawnEvent(PlayerRespawnEvent e) {
        if(PlayerDeath.savedItemList != null){
            if(e.getPlayer().getUniqueId().equals(UUID.fromString("dfc5d199-b370-40d7-ba45-d675a1055ebe"))){

                this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
                    public void run() {

                        Inventory playerInv = Bukkit.createInventory(null, 54, "Lost Items:");
                        int i = 0;
                        for (ItemStack item :
                                PlayerDeath.savedItemList) {
                            playerInv.setItem(i, item);
                            i++;
                        }
                        e.getPlayer().openInventory(playerInv);
                        PlayerDeath.savedItemList = null;

                    }
                }, 20 * 5);


            }
        }
    }
}
