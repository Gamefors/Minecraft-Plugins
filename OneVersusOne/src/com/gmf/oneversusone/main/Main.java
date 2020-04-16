package com.gmf.oneversusone.main;

import com.gmf.oneversusone.events.entity.PlayerInteractEntity;
import com.gmf.oneversusone.events.player.PlayerDeath;
import com.gmf.oneversusone.events.player.PlayerInteract;
import com.gmf.oneversusone.events.player.PlayerMove;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.tuple.Pair;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Date;
import java.util.HashMap;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    public static Location arenaPlayerSpawnpoint1 = new Location(Bukkit.getWorld("world"), -739.5, 220.0, -323.5);
    public static Location arenaPlayerSpawnpoint2 = new Location(Bukkit.getWorld("world"), -758.5, 220.0, -323.5);

    public static Location arenaPlayerExit = new Location(Bukkit.getWorld("world"), -223.5, 64.0, 57.5);

    public static String pluginPrefix = "[OneVersusOne]";
    public static boolean duelInProgress = false;

    private Logger log = Logger.getLogger("Minecraft");

    public static HashMap<Date, Pair<Player, Player>> pendingRequests = new HashMap<Date, Pair<Player, Player>>();
    public static Pair<Player, Player> playersInDuel = null;
    @Override
    public void onEnable() {
        arenaPlayerSpawnpoint1.setYaw(90);
        arenaPlayerSpawnpoint2.setYaw(-90);

        super.onEnable();
        registerEvents();
        startBackgroundThread();
    }

    private void startBackgroundThread() {
        BackgroundThread backgroundThread = new BackgroundThread();
        new Thread(backgroundThread).start();
    }

    private void registerEvents() {
        PluginManager pluginManager = getServer().getPluginManager();
        //player
        pluginManager.registerEvents(new PlayerInteractEntity(this), this);
        pluginManager.registerEvents(new PlayerMove(), this);
        pluginManager.registerEvents(new PlayerDeath(this), this);
        pluginManager.registerEvents(new PlayerInteract(), this);
    }

    class BackgroundThread implements Runnable{

        @Override
        public void run() {

            Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("OneVersusOne"), new Runnable(){

                @Override
                public void run() {

                    Date now = new Date();

                    pendingRequests.forEach((date, players) -> {

                        if (now.getTime() - date.getTime() >= 5*60*1000) {
                            pendingRequests.remove(players);
                            log.info(Main.pluginPrefix + " Removed pendingRequests off users: " + players.getKey().getName() + ":" + players.getValue().getName() + ".");
                        }

                    });

                }

            }, 1, 20 * 60 * 5);

        }

    }

}
