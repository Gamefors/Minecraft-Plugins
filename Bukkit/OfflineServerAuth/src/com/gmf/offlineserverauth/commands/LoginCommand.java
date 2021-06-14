package com.gmf.offlineserverauth.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginCommand implements CommandExecutor {

    public static List<String> loggedInUsers = new ArrayList<String>();

    HashMap<String, String> accounts = new HashMap<>() {{
        put("Gamefors", "4816");
        put("Thieltges", "7390");

        put("Cedric", "4859");
        put("Cheng-Fu", "6497");
        put("Felix", "7390");
        put("Flemming", "1824");
        put("Jan", "4816");
        put("Jerome", "2734");
        put("Jonah", "0573");
        put("Jonas", "0867");
        put("Sergej", "4926");
        put("Soeren", "0187");
        put("Timon", "0897");
        put("Tobias", "4136");
        put("Bra", "4908");

    }};

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if(accounts.containsKey(p.getName())){
            try {
                if(accounts.get(p.getName()).equals(strings[0])){
                    loggedInUsers.remove(p.getName());
                    loggedInUsers.add(p.getName());
                    p.sendMessage(ChatColor.GREEN + "You are now logged in.");
                }else{
                    p.sendMessage(ChatColor.RED + "The password you entered was wrong.");
                }
            }
            catch (Exception ex){
                p.sendMessage(ChatColor.RED + "You need to enter a 4-digit password.");
            }

        }else{
            p.sendMessage(ChatColor.RED + "You username is not registered.");
        }
        return true;
    }
}
