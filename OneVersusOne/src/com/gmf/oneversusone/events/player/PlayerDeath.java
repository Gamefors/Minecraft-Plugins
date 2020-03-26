package com.gmf.oneversusone.events.player;

import com.gmf.oneversusone.main.Main;
import javafx.util.Pair;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

    Main plugin;

    public PlayerDeath(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent e) {

        if (e.getEntity() instanceof Player) {

            if (e.getEntity().getKiller() instanceof Player) {

                Player killedPlayer = e.getEntity();
                Player killerPlayer = e.getEntity().getKiller();
                if(Main.playersInDuel != null){
                    if (killedPlayer == Main.playersInDuel.getValue() || killedPlayer == Main.playersInDuel.getKey()) {

                        Main.playersInDuel = null;
                        Main.duelInProgress = false;

                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.sendMessage(Main.pluginPrefix + " " + killerPlayer.getDisplayName() + " won the duel.");
                        }

                        this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
                            public void run() {
                                killedPlayer.teleport(Main.arenaPlayerExit);
                                killerPlayer.teleport(Main.arenaPlayerExit);
                            }
                        }, 20 * 1);

                        this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
                            public void run() {
                                Bukkit.getWorld("world").setGameRule(GameRule.KEEP_INVENTORY, false);
                            }
                        }, 20 * 30);

                    }
                }

            } else {
                Player killedPlayer = e.getEntity();
                if(Main.playersInDuel != null){
                    if (killedPlayer == Main.playersInDuel.getValue() || killedPlayer == Main.playersInDuel.getKey()) {

                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.sendMessage(Main.pluginPrefix + " " + killedPlayer.getName() + " was not killed by a player.");
                        }

                        this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
                            public void run() {
                                Main.playersInDuel.getKey().teleport(Main.arenaPlayerExit);
                                Main.playersInDuel.getValue().teleport(Main.arenaPlayerExit);

                                Main.playersInDuel = null;
                                Main.duelInProgress = false;
                            }
                        }, 20 * 1);

                        this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
                            public void run() {
                                Bukkit.getWorld("world").setGameRule(GameRule.KEEP_INVENTORY, false);
                            }
                        }, 20 * 30);
                    }

                }

            }

        }

    }
}
