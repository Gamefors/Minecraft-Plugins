package com.gmf.skyblock.commands;

import com.gmf.skyblock.main.Main;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class IsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if (strings.length == 0) {
            Main.visitingPlayers.removeIf(players -> players.get(p) != null);
            try {
                p.setGameMode(GameMode.SURVIVAL);
                p.teleport(Main.mysqlHelper.getIslandByUUID(p.getUniqueId()).spawn);
                p.sendMessage(Main.prefix + " Your have been teleported to your island.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            p.sendMessage(Main.prefix + " §c/is.");
        }



        return true;
    }
}
