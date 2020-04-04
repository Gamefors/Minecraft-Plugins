package com.gmf.skyblock.events.inventory;

import com.gmf.skyblock.main.Main;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.sql.SQLException;

public class InventoryClick implements Listener {

    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent e){

        Player p = (Player) e.getWhoClicked();

        if(e.getCurrentItem() != null){
            if(e.getCurrentItem().hasItemMeta()){
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Skyblock Menu")) {
                    e.setCancelled(true);

                }
            }
            if(p.getOpenInventory().getTitle().equals("§6Skyblock Menu")){
                if(e.getCurrentItem().hasItemMeta()){
                    if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§2Teleport yourself to your island.")) {
                        e.setCancelled(true);
                        Main.visitingPlayers.removeIf(players -> players.get(p) != null);
                        try {
                            p.setGameMode(GameMode.SURVIVAL);
                            p.teleport(Main.mysqlHelper.getIslandByUUID(p.getUniqueId()).spawn);
                            p.sendMessage(Main.prefix + " Your have been teleported to your island.");
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }else{
                        e.setCancelled(true);
                    }
                }
            }

        }

    }

}
