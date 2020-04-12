package com.gmf.skyblock.events.inventory;

import com.gmf.skyblock.main.Main;
import com.gmf.skyblock.utils.IslandManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.sql.SQLException;

public class InventoryClick implements Listener {

    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent e) throws SQLException {

        Player p = (Player) e.getWhoClicked();
        IslandManager islandManager = new IslandManager(p, Main.mysqlHelper.getIslandByUUID(p.getUniqueId()));

        if(e.getCurrentItem() != null){
            if(e.getCurrentItem().hasItemMeta()){
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§b§6Skyblock Menu §7(Right click)")) {
                    e.setCancelled(true);

                }
            }

            if(p.getOpenInventory().getTitle().equals("§b§6Skyblock Menu")){
                if(e.getCurrentItem().hasItemMeta()){
                    if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§2Teleports you to your island.")) {
                        e.setCancelled(true);
                        Main.visitingPlayers.removeIf(players -> players.get(p) != null);
                        islandManager.teleportPlayerToIsland();
                    }else{
                        e.setCancelled(true);
                    }
                }
            }

        }

    }

}
