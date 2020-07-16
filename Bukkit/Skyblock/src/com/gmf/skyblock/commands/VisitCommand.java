package com.gmf.skyblock.commands;

import com.gmf.skyblock.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VisitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if (strings.length != 0) {
            String toVisitPlayerString = strings[0];
            Player toVisitPlayer = Bukkit.getServer().getPlayer(toVisitPlayerString);
            if(toVisitPlayer != null){

                if(Bukkit.getServer().getPlayer(toVisitPlayerString) != p){
                    if(Main.visitingPlayers.isEmpty()){
                        p.sendMessage(Main.prefix + " You visit " + toVisitPlayer.getName() + "'s island.");
                        toVisitPlayer.sendMessage(Main.prefix + " " + p.getName() + " is visiting your island.");
                        p.setGameMode(GameMode.ADVENTURE);
                        HashMap<Player,Player> players2 = new HashMap<Player,Player>();
                        p.sendMessage(Main.visitingPlayers.toString());
                        p.sendMessage(p.getName() + " visits: " + toVisitPlayer.getName());
                        players2.put(p, toVisitPlayer);
                        Main.visitingPlayers.add(players2);
                        try {
                            p.teleport(Main.mysqlHelper.getIslandByUUID(toVisitPlayer.getUniqueId()).spawn);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }else{
                        List<HashMap<Player, Player>> temp = new ArrayList<HashMap<Player, Player>>();
                        for (HashMap<Player, Player> players :
                                Main.visitingPlayers) {
                            temp.add(players);
                            if(players.get(p) == toVisitPlayer){
                                p.sendMessage(Main.prefix + " You are already visiting " + toVisitPlayer.getName() + "'s Island.");
                            }else{
                                p.sendMessage(Main.prefix + " You visit " + toVisitPlayer.getName() + "'s island.");
                                toVisitPlayer.sendMessage(Main.prefix + " " + p.getName() + " is visiting your island.");
                                p.setGameMode(GameMode.ADVENTURE);
                                p.sendMessage(Main.visitingPlayers.toString());
                                p.sendMessage(p.getName() + " visits: " + toVisitPlayer.getName());
                                HashMap<Player,Player> players23 = new HashMap<Player,Player>();
                                players23.put(p, toVisitPlayer);
                                temp.add(players23);
                                try {

                                        p.teleport(Main.mysqlHelper.getIslandByUUID(toVisitPlayer.getUniqueId()).spawn);
                                } catch (SQLException e) {
                                        e.printStackTrace();
                                }
                            }
                        }
                        Main.visitingPlayers.clear();
                        Main.visitingPlayers.addAll(temp);
                    }

                }else{
                    p.sendMessage(Main.prefix + " You cant visit yourself.");
                }
            }else{
                p.sendMessage(Main.prefix + " This player does not exits.");
            }


        } else {
            p.sendMessage(Main.prefix + " §c/visit [username]");
        }


        return true;
    }
}
