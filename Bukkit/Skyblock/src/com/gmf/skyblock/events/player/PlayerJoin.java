package com.gmf.skyblock.events.player;

import com.gmf.skyblock.main.Main;
import com.gmf.skyblock.objects.Island;
import com.gmf.skyblock.utils.IslandManager;
import com.gmf.skyblock.utils.WorldEditHelper;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.rmi.UnexpectedException;
import java.sql.SQLException;
import java.util.List;

public class PlayerJoin implements Listener {

    Main plugin;

    public PlayerJoin(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e) throws SQLException, ClassNotFoundException, UnexpectedException {

        WorldEditHelper worldEditHelper = new WorldEditHelper(plugin);

        Player p = e.getPlayer();

        p.getInventory().setItem(8, Main.itemList.get(0));

        List<Island> islands = Main.mysqlHelper.getIslands();

        Island pIsland = null;

        if(islands.size() != 0){
            pIsland = Main.mysqlHelper.getIslandByUUID(p.getUniqueId());

            if(pIsland == null){
                if(islands.get(islands.size() - 1) != null){
                    Island lastIsland = islands.get(islands.size() - 1);
                    Main.mysqlHelper.addIsland(p.getUniqueId(), new Location(plugin.getServer().getWorld("world"), lastIsland.spawn.getX() - 5000, lastIsland.spawn.getY(), lastIsland.spawn.getZ()));
                    worldEditHelper.placeSkyblockStartIsland(new Location(plugin.getServer().getWorld("world"), lastIsland.spawn.getX() - 5000,lastIsland.spawn.getY(),lastIsland.spawn.getZ()));

                    pIsland = Main.mysqlHelper.getIslandByUUID(p.getUniqueId());
                }
            }

        }else{

            Main.mysqlHelper.addIsland(p.getUniqueId(), new Location(plugin.getServer().getWorld("world"), 0,64,0));
            worldEditHelper.placeSkyblockStartIsland(new Location(plugin.getServer().getWorld("world"), 0,64,0));

            pIsland = Main.mysqlHelper.getIslandByUUID(p.getUniqueId());

        }
        IslandManager islandManager = new IslandManager(p, pIsland);
        islandManager.teleportPlayerToIsland();
    }

}
