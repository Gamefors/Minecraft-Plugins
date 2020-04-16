package com.gmf.cmd.commands;

import com.gmf.cmd.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class NickCommand implements CommandExecutor {

    public static List<String> nickedPlayers = new ArrayList<String>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] strings) {
        Player p = (Player) commandSender;
        if(p.hasPermission("cmd.nick")){
            if (strings.length != 0) {
                if(strings.length != 1){
                    String newName = strings[1];

                    if(Bukkit.getServer().getPlayer(strings[0]) == null){
                        p.sendMessage(Main.prefix + " §cThe specified username could not be found on the server.");
                    }else{
                        Player playerToChangeNameFor = Bukkit.getServer().getPlayer(strings[0]);
                        playerToChangeNameFor.setDisplayName(newName);
                        playerToChangeNameFor.setPlayerListName(newName);
                        p.sendMessage(Main.prefix + " The name of: §c" + Bukkit.getServer().getPlayer(strings[0]).getName() + " §fis now: §a" + newName);
                        if(!nickedPlayers.contains(playerToChangeNameFor.getName())){
                            nickedPlayers.add(playerToChangeNameFor.getName());
                        }
                    }
                }else{
                    p.sendMessage(Main.prefix + " §c/nick [username] §4[newNickName]");
                }

            } else {
                p.sendMessage(Main.prefix + " §c/nick [username] [newNickName]");
            }
        }else{
            p.sendMessage("§cYou are not allowed to do that.");
        }


        return true;
    }

}
