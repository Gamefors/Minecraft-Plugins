package com.gmf.skyblock.commands;

import com.gmf.skyblock.main.Main;
import com.gmf.skyblock.utils.IslandManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class IsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        try {
            IslandManager islandManager = new IslandManager(p, Main.mysqlHelper.getIslandByUUID(p.getUniqueId()));
            if (strings.length == 0) {
                Main.visitingPlayers.removeIf(players -> players.get(p) != null);
                islandManager.teleportPlayerToIsland();
            }else{
                p.sendMessage(Main.prefix + " §c/is.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
