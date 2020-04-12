package com.gmf.skyblock.events.player;

import com.gmf.skyblock.main.Main;
import com.gmf.skyblock.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.HashMap;

public class PlayerInteract implements Listener {

    @EventHandler
    public void playerInteractEvent(PlayerInteractEvent e) throws SQLException {

        Player p = e.getPlayer();
        ItemStack selectedItem = p.getInventory().getItemInMainHand();

        for (HashMap<Player, Player> players :
                Main.visitingPlayers) {
            if(players.get(p) != null){
                if(!p.isOp()) {
                    e.setCancelled(true);
                }
            }

        }

        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if(selectedItem.getItemMeta() != null){
                if (selectedItem.getItemMeta().getDisplayName().equals("§b§6Skyblock Menu §7(Right click)")) {

                    ItemStack toIs = new ItemBuilder(Material.GRASS_BLOCK).setDisplayName("§2Teleports you to your island.").toItemStack();

                    Inventory skyblockMenu = Bukkit.createInventory(null, 27, "§b§6Skyblock Menu");
                    skyblockMenu.setItem(13, toIs);
                    p.openInventory(skyblockMenu);

                }
            }
        }

    }

}