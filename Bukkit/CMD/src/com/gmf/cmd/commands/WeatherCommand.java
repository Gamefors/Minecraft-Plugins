package com.gmf.cmd.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WeatherCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if(p.hasPermission("cmd.weather")){
            if(s.contains("sun")){
                p.getWorld().setStorm(false);
                p.sendMessage("Weather changed to sun.");
            }else if(s.contains("rain")){
                p.getWorld().setStorm(true);
                p.sendMessage("Weather changed to rain.");
            }else{
                p.sendMessage("§4ERROR");
            }
        }else{
            p.sendMessage("§cYou are not allowed to do that.");
        }
        return true;
    }
}
