package com.gmf.skyblock.events.player;

import com.gmf.skyblock.main.Main;
import com.gmf.skyblock.objects.Island;
import com.gmf.skyblock.utils.MysqlHelper;
import com.gmf.skyblock.utils.WorldEditHelper;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;
import java.util.List;

public class PlayerJoin implements Listener {

    Main plugin;

    public PlayerJoin(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e) throws SQLException, ClassNotFoundException {
        WorldEditHelper worldEditHelper = new WorldEditHelper(plugin);


        Player p = e.getPlayer();

        ItemStack skyblockMenu = new ItemStack(Material.NETHER_STAR);

        ItemMeta im = skyblockMenu.getItemMeta();
        im.setDisplayName("§6Skyblock Menu");
        im.addEnchant(Enchantment.ARROW_FIRE,1,true);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        skyblockMenu.setItemMeta(im);

        p.getInventory().setItem(8, skyblockMenu);

        List<Island> islands = Main.mysqlHelper.getIslands();
        
        if(islands.size() != 0){
            Island pIsland = null;
            for (Island island :
                    islands) {
                if (island.owner.equals(p.getUniqueId())) {
                    p.teleport(island.spawn);
                    pIsland = island;
                    p.setGameMode(GameMode.SURVIVAL);
                    p.sendMessage(Main.prefix + " Your have been teleported to your island.");
                }
            }

            if(pIsland == null){
                if(islands.get(islands.size() - 1) != null){
                    Island lastIsland = islands.get(islands.size() - 1);
                    p.setGameMode(GameMode.SURVIVAL);
                    Main.mysqlHelper.addIsland(p.getUniqueId(), new Location(plugin.getServer().getWorld("world"), lastIsland.spawn.getX() - 5000, lastIsland.spawn.getY(), lastIsland.spawn.getZ()));
                    p.teleport(new Location(plugin.getServer().getWorld("world"), lastIsland.spawn.getX() - 5000,lastIsland.spawn.getY(),lastIsland.spawn.getZ()));
                    worldEditHelper.placeSkyblockStartIsland(new Location(plugin.getServer().getWorld("world"), lastIsland.spawn.getX() - 5000,lastIsland.spawn.getY(),lastIsland.spawn.getZ()));
                    p.sendMessage(Main.prefix + " Your have been teleported to your island.");

                }
            }

        }else{
            p.setGameMode(GameMode.SURVIVAL);
            p.teleport(new Location(plugin.getServer().getWorld("world"), 0,64,0));
            Main.mysqlHelper.addIsland(p.getUniqueId(), new Location(plugin.getServer().getWorld("world"), 0,64,0));
            worldEditHelper.placeSkyblockStartIsland(new Location(plugin.getServer().getWorld("world"), 0,64,0));
            p.sendMessage(Main.prefix + " Your have been teleported to your island.");

        }

    }

}
