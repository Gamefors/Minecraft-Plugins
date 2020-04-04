package com.gmf.skyblock.events.player;

import com.gmf.skyblock.main.Main;
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

import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class PlayerInteract implements Listener {

    @EventHandler
    public void playerInteractEvent(PlayerInteractEvent e) throws SQLException {

        Player p = e.getPlayer();
        ItemStack selectedItem = p.getInventory().getItemInMainHand();

        for (HashMap<Player, Player> players :
                Main.visitingPlayers) {
            if(players.get(p) != null){
                if(!p.getUniqueId().equals(UUID.fromString("6212c912-dc16-3f22-a719-29be6a6b53e2"))) {
                    e.setCancelled(true);
                }
            }

        }

        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if(selectedItem.getItemMeta() != null){
                if (selectedItem.getItemMeta().getDisplayName().equals("§6Skyblock Menu")) {

                    ItemStack toIs = new ItemStack(Material.GRASS_BLOCK);
                    ItemMeta toIsItemMeta = toIs.getItemMeta();
                    toIsItemMeta.setDisplayName("§2Teleport yourself to your island.");
                    toIs.setItemMeta(toIsItemMeta);

                    Inventory skyblockMenu = Bukkit.createInventory(null, 27, "§6Skyblock Menu");
                    skyblockMenu.setItem(13, toIs);
                    p.openInventory(skyblockMenu);

                }
            }
        }

    }

}