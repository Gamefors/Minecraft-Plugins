package com.gmf.cmd.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if(p.hasPermission("cmd.time")){
            if(s.equals("day")){
                p.getWorld().setTime(30000);
                p.sendMessage("Time changed to Daytime.");
            }else if(s.equals("night")){
                p.getWorld().setTime(18000);
                p.sendMessage("Time changed to Nighttime.");
            }else{
                p.sendMessage("§4ERROR");
            }
        }else{
            p.sendMessage("§cYou are not allowed to do that.");
        }
        return true;
    }
}
