package com.gmf.skyblock.events.player;

import com.gmf.skyblock.main.Main;
import com.gmf.skyblock.objects.Island;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;

public class PlayerRespawn implements Listener {

    @EventHandler
    public void playerRespawnEvent(PlayerRespawnEvent e) throws SQLException, ClassNotFoundException {
        Player p = e.getPlayer();
        Island pIsland = Main.mysqlHelper.getIslandByUUID(p.getUniqueId());
        e.setRespawnLocation(pIsland.spawn);

        ItemStack skyblockMenu = new ItemStack(Material.NETHER_STAR);

        ItemMeta im = skyblockMenu.getItemMeta();
        im.setDisplayName("§6Skyblock Menu");
        im.addEnchant(Enchantment.ARROW_FIRE,1,true);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        skyblockMenu.setItemMeta(im);

        p.getInventory().setItem(8, skyblockMenu);
    }

}
