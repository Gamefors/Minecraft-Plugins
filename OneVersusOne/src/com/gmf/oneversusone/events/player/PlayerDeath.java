package com.gmf.oneversusone.events.player;

import com.gmf.oneversusone.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

    Main plugin;

    public PlayerDeath(Main plugin){
        this.plugin = plugin;

    }


    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent e) {



        Player killed = e.getEntity();
        Player killer = e.getEntity();
        if(Main.fightingPlayers.get(killed) == "pos1" || Main.fightingPlayers.get(killed) == "pos2"){

            Main.duelInProgress = false;
            Main.duelling = false;
            Main.fightingPlayers.remove(killed);
            Main.fightingPlayers.remove(killer);

            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(Main.pluginPrefix + " " + killer.getDisplayName() + " won the duel.");
            }


            this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() { public void run() { Bukkit.getWorld("world").setGameRule(GameRule.KEEP_INVENTORY, false);
            } }, 20 * 30);

        }
    }
}
