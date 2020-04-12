package com.gmf.skyblock.events.player;

import com.gmf.skyblock.main.Main;
import com.gmf.skyblock.utils.IslandManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.sql.SQLException;

public class PlayerRespawn implements Listener {

    @EventHandler
    public void playerRespawnEvent(PlayerRespawnEvent e) throws SQLException{
        Player p = e.getPlayer();
        IslandManager islandManager = new IslandManager(p, Main.mysqlHelper.getIslandByUUID(p.getUniqueId()));
        islandManager.teleportPlayerToIsland();
    }

}
