package com.gmf.cmd.commands;

import com.gmf.cmd.main.Main;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class GamemodeCommand implements Listener {

    @EventHandler
    public boolean playerCommandPreprocessEvent(PlayerCommandPreprocessEvent e) {

        String cmd = e.getMessage().split(" ")[0].replace("/", "").toLowerCase();
        Player p = e.getPlayer();

        if (cmd.equals("gamemode") || cmd.equals("gm")) {
            if(p.hasPermission("cmd.gamemode")){
                e.setCancelled(true);
                if(e.getMessage().split(" ").length <= 1){
                    p.sendMessage(Main.prefix + " §c/gm §7[survival(0)/creative(1)/adventure(2)/spectator(3)]");
                }else{
                    if(e.getMessage().split(" ")[1].toLowerCase().equals("0") || e.getMessage().split(" ")[1].toLowerCase().equals("survival")){
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage("Your §5gamemode §fhas been set to §asurvival§f.");
                    }else if(e.getMessage().split(" ")[1].toLowerCase().equals("1") || e.getMessage().split(" ")[1].toLowerCase().equals("creative")){
                        p.setGameMode(GameMode.CREATIVE);
                        p.sendMessage("Your §5gamemode §fhas been set to §ccreative§f.");
                    }else if(e.getMessage().split(" ")[1].toLowerCase().equals("2") || e.getMessage().split(" ")[1].toLowerCase().equals("adventure")){
                        p.setGameMode(GameMode.ADVENTURE);
                        p.sendMessage("Your §5gamemode §fhas been set to §6adventure§f.");
                    }else if(e.getMessage().split(" ")[1].toLowerCase().equals("3") || e.getMessage().split(" ")[1].toLowerCase().equals("spectator")){
                        p.setGameMode(GameMode.SPECTATOR);
                        p.sendMessage("Your §5gamemode §fhas been set to §7spectator§f.");
                    }else{
                        p.sendMessage("§cInvalid gamemode.");
                    }
                }
            }else{
                p.sendMessage("§cYou are not allowed to do that.");
            }
            }

        return true;
    }

}
