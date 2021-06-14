package com.gmf.offlineserverauth.events;

import com.gmf.offlineserverauth.commands.LoginCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!LoginCommand.loggedInUsers.contains(e.getPlayer().getName())){
            p.sendMessage(ChatColor.RED + "##################################");
            p.sendMessage("");
            p.sendMessage(ChatColor.GOLD + "Please login with /login <password>");
            p.sendMessage("");
            p.sendMessage(ChatColor.RED + "##################################");
        }
    }
}
