package com.gmf.oneversusone.events.entity;

import com.gmf.oneversusone.main.Main;
import javafx.util.Pair;
import org.bukkit.GameRule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Date;

public class PlayerInteractEntity implements Listener {

    Main plugin;

    public PlayerInteractEntity(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void playerInteractEntityEvent(PlayerInteractEntityEvent e) {

        EquipmentSlot equipmentSlot = e.getHand();

        if (equipmentSlot.equals(EquipmentSlot.HAND)) {

            if (e.getRightClicked() instanceof Player) {

                Player challengedPlayer = (Player) e.getRightClicked();
                Player p = e.getPlayer();

                if (p.isSneaking()) {

                    if (!Main.duelInProgress) {

                        if(Main.pendingRequests.isEmpty()){

                            p.sendMessage(Main.pluginPrefix + " You challenged " + challengedPlayer.getName() + " to a §aduel§f.");
                            challengedPlayer.sendMessage(Main.pluginPrefix + " " + p.getName() + " challenged you to a §aduel§f.");
                            Main.pendingRequests.put(new Date(), new Pair<Player, Player>(p, challengedPlayer));

                        }else{

                            Main.pendingRequests.forEach((date, players) -> {

                                if(players.getValue() == challengedPlayer){

                                    p.sendMessage(Main.pluginPrefix + " There is already a request pending for " + challengedPlayer.getName() + ".");

                                }else{

                                    p.sendMessage(Main.pluginPrefix + " You already challenged a player.");

                                }

                                if(players.getKey() != p){
                                    if(players.getKey() == challengedPlayer){
                                        startDuel(p);
                                    }


                                }

                            });
                        }

                    }else{

                        p.sendMessage(Main.pluginPrefix + " There is already a duel in progress.");

                    }

                }

            }

        }

    }

    private void startDuel(Player p) {
        Main.pendingRequests.forEach((date, players) -> {
            final Player challenger = players.getKey();

            challenger.sendMessage(Main.pluginPrefix + " " + p.getName() + " accepted your §cduel§f.");
            p.sendMessage(Main.pluginPrefix + " You accepted the §cduel §fagainst " + challenger.getName() + ".");

            Main.playersInDuel = new Pair<Player, Player>(p, challenger);

            this.plugin.getServer().getWorld("world").setPVP(false);
            this.plugin.getServer().getWorld("world").setGameRule(GameRule.KEEP_INVENTORY, true);

            p.teleport(Main.arenaPlayerSpawnpoint1);
            challenger.teleport(Main.arenaPlayerSpawnpoint2);

            challenger.sendMessage(Main.pluginPrefix + " Duel starting in 3sec...");
            p.sendMessage(Main.pluginPrefix + " Duel starting in 3sec...");

            this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
                public void run() {
                    p.sendMessage(Main.pluginPrefix + " Duel starting in 2sec...");
                    challenger.sendMessage(Main.pluginPrefix + " Duel starting in 2sec...");
                }
            }, 20 * 3);

            this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
                public void run() {
                    p.sendMessage(Main.pluginPrefix + " Duel starting in 1sec...");
                    challenger.sendMessage(Main.pluginPrefix + " Duel starting in 1sec...");
                }
            }, 20 * 5);

            this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
                public void run() {
                    Main.duelInProgress = true;
                    plugin.getServer().getWorld("world").setPVP(true);
                    p.sendMessage(Main.pluginPrefix + " Duel started.");
                    challenger.sendMessage(Main.pluginPrefix + " Duel started.");
                    Main.pendingRequests.remove(date);
                }
            }, 20 * 6);

        });

    }

}