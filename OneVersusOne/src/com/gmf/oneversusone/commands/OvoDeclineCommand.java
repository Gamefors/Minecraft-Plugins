package com.gmf.oneversusone.commands;

import com.gmf.oneversusone.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OvoDeclineCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player)commandSender;
        if(Main.pendingRequest.get(p) == null){
            p.sendMessage(Main.pluginPrefix + " You dont have a pending duel request.");
            return true;
        }
        Player challenger = Main.pendingRequest.get(p);
        challenger.sendMessage(Main.pluginPrefix + " "  + p.getName() + " declined your §cduel §frequest.");
        p.sendMessage(Main.pluginPrefix + " You declined "  + challenger.getName() + "'s §cduel §frequest.");
        Main.pendingRequest.remove(p);
        return true;
    }
}
