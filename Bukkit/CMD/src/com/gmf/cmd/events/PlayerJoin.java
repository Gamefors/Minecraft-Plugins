package com.gmf.cmd.events;

import com.gmf.cmd.commands.VanishCommand;
import com.gmf.cmd.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    Main plugin;

    public PlayerJoin(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e) {
        if(VanishCommand.vanishedPlayers.contains(e.getPlayer().getName())){
            e.setJoinMessage("");
        }
        for (String pName : VanishCommand.vanishedPlayers) {
            try{
                e.getPlayer().hidePlayer(this.plugin, Bukkit.getPlayer(pName));
            }catch (IllegalArgumentException ex){

            }
        }
        for (Player p  : Bukkit.getOnlinePlayers()) {
            try{
                if(VanishCommand.vanishedPlayers.contains(e.getPlayer().getName())){
                    try{
                        p.hidePlayer(this.plugin, e.getPlayer());
                    }catch (IllegalArgumentException ex){

                    }
                }
            }catch (IllegalArgumentException ex){

            }
        }
    }

}
