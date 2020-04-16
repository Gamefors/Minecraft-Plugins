package com.gmf.cmd.commands;

import com.gmf.cmd.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class VanishCommand implements CommandExecutor{

    Main plugin;

    public static List<String> vanishedPlayers = new ArrayList<String>();

    public VanishCommand(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if(p.hasPermission("cmd.vanish")){
            if(vanishedPlayers.contains(p.getName())){
                for(Player pl : Bukkit.getOnlinePlayers()){
                    pl.showPlayer(this.plugin, p);
                }
                vanishedPlayers.remove(p.getName());
                p.sendMessage(Main.prefix + " §CUnvanished.");
            }else{
                for(Player pl : Bukkit.getOnlinePlayers()){
                    pl.hidePlayer(this.plugin, p);
                }
                vanishedPlayers.add(p.getName());
                p.sendMessage(Main.prefix + " §6Vanished.");
            }

        }else{
            p.sendMessage("§cYou are not allowed to do that.");
        }
        return true;
    }

}
