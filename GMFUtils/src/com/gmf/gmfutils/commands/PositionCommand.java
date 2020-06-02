package com.gmf.gmfutils.commands;

import com.gmf.gmfutils.main.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PositionCommand implements CommandExecutor {

    HashMap<String, Location> positions = new HashMap<String, Location>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player)commandSender;
        if (strings.length != 0) {
            String positionName = strings[0];
            if(positions.containsKey(positionName)){
                p.sendMessage(Main.prefix + "§6 Coordinates for position §a" + positionName + "§f:");
                p.sendMessage("§cx: " + p.getLocation().getX());
                p.sendMessage("§2y: " + p.getLocation().getY());
                p.sendMessage("§bz: " + p.getLocation().getZ());
            }else{
                Location positionLocation = p.getLocation();
                positions.put(positionName, positionLocation);
                p.sendMessage(Main.prefix + "new position with name: §a" + positionName + " §fadded.");
                p.sendMessage("§6Coordinates:");
                p.sendMessage("§cx: " + p.getLocation().getX());
                p.sendMessage("§2y: " + p.getLocation().getY());
                p.sendMessage("§bz: " + p.getLocation().getZ());
            }

        }else {
            p.sendMessage("§6Positions:");
            positions.forEach((positionName, positionLocation) -> p.sendMessage("§c" + positionName));
        }


        return true;
    }
}
