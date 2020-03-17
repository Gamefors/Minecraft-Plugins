package com.gamefors.gmf.commands;

import com.gamefors.gmf.main.Main;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandGm implements CommandExecutor {



    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if(strings.length != 0){
                switch (strings[0]){
                    case "0":
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage((Main.prefix + " Your gamemode has been updated to §aSURVIVAL§f."));
                        break;
                    case "1":
                        p.setGameMode(GameMode.CREATIVE);
                        p.sendMessage((Main.prefix + " Your gamemode has been updated to §aCREATIVE§f."));
                        break;
                    case "2":
                        p.setGameMode(GameMode.ADVENTURE);
                        p.sendMessage((Main.prefix + " Your gamemode has been updated to §aADVENTURE§f."));
                        break;
                    case "3":
                        p.setGameMode(GameMode.SPECTATOR);
                        p.sendMessage((Main.prefix + " Your gamemode has been updated to §aSPECTATOR§f."));
                        break;
                    default:
                        p.sendMessage((Main.prefix + Main.errorPrefix + " Gamemodes are from 0-3."));
                }
            }else{
                p.sendMessage((Main.prefix + Main.errorPrefix + " No argument supplied."));
            }
        }
        return true;
    }
}
