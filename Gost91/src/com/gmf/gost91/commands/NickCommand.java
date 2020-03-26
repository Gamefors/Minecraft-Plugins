package com.gmf.gost91.commands;

import com.gmf.gost91.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NickCommand implements CommandExecutor {

    private void setPlayerName(Player p, String newName){
        p.sendMessage(Main.pluginPrefix + " Your name is now: §a" + newName);
        p.setDisplayName(newName);
        p.setPlayerListName(newName);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] strings) {
        Player p = (Player) commandSender;
        if(p.isOp()){
            if (strings.length != 0) {
                if(strings.length != 1){
                    String newName = strings[1];

                    if(Bukkit.getServer().getPlayer(strings[0]) == null){
                        p.sendMessage(Main.pluginPrefix + " §cThe specified username could not be found on the server.");
                    }else{
                        setPlayerName(Bukkit.getServer().getPlayer(strings[0]),newName);
                        p.sendMessage(Main.pluginPrefix + " The name of: §c" + Bukkit.getServer().getPlayer(strings[0]).getName() + " §fis now: §a" + newName);
                    }
                }else{
                    p.sendMessage(Main.pluginPrefix + " §c/nick [username] §4[newNickName]");
                }

            } else {
                p.sendMessage(Main.pluginPrefix + " §c/nick [username] [newNickName]");
            }
        }else{
            p.sendMessage(Main.pluginPrefix + " You are not a server operator.");
        }


        return true;
    }

}
