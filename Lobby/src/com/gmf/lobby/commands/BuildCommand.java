package com.gmf.lobby.commands;

import com.gmf.lobby.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BuildCommand implements CommandExecutor {

    public static List<String> builders = new ArrayList<String>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if(p.hasPermission("lobby.build")){
            if(builders.contains(p.getName())){
                builders.remove(p.getName());
                p.sendMessage(Main.prefix + " You are §cnot allowed §fto build anymore.");
            }else{
                builders.add(p.getName());
                p.sendMessage(Main.prefix + " You are now §aallowed §fto build.");
            }
        }else{
            p.sendMessage("§cYou are not allowed to do that.");
        }
        return true;
    }
}
