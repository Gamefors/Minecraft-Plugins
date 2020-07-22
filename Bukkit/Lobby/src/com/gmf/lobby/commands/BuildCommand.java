package com.gmf.lobby.commands;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;

public class BuildCommand implements CommandExecutor, Listener {

    public static List<String> builders = new ArrayList<String>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if(p.hasPermission("lobby.build")){
            if(builders.contains(p.getName())){
                builders.remove(p.getName());
                p.setGameMode(GameMode.ADVENTURE);
                p.sendMessage("You are §cnot allowed §fto build anymore.");
            }else{
                builders.add(p.getName());
                p.setGameMode(GameMode.CREATIVE);
                p.sendMessage("You are now §aallowed §fto build.");
            }
        }else{
            p.sendMessage("§cYou are not allowed to do that.");
        }
        return true;
    }

    @EventHandler
    public void playerMoveEvent(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if(p.getLocation().getBlockY() < 0){
            if(!BuildCommand.builders.contains(e.getPlayer().getName())){
                p.teleport(new Location(p.getWorld(),0.5, 1,0.5));
            }
        }
    }

}
