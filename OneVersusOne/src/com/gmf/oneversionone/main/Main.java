package com.gmf.oneversionone.main;

import com.gmf.oneversionone.commands.OvoAcceptCommand;
import com.gmf.oneversionone.commands.OvoDeclineCommand;
import com.gmf.oneversionone.events.entity.PlayerInteractEntity;
import com.gmf.oneversionone.events.player.PlayerDeath;
import com.gmf.oneversionone.events.player.PlayerMove;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main extends JavaPlugin {


    public static int xOffArenaPos1 = -264;
    public static int yOffArenaPos1 = 64;
    public static int zOffArenaPos1 = 16;


    public static int xOffArenaPos2 = -270;
    public static int yOffArenaPos2 = 64;
    public static int zOffArenaPos2 = 16;


    public static String pluginPrefix = "[OneVersusOne]";
    public static boolean duelInProgress = false;
    public static boolean duelling = false;

    //TODO: add thread that removes pending requests longer than 5min
    public static HashMap<Player, Player> pendingRequest = new HashMap<>();
    public static HashMap<Player, String> fightingPlayers = new HashMap<>();
    @Override
    public void onEnable() {
        super.onEnable();
        registerEvents();
        registerCommands();
    }

    private void registerCommands() {
        getCommand("ovoaccept").setExecutor(new OvoAcceptCommand(this));
        getCommand("ovodecline").setExecutor(new OvoDeclineCommand());
    }

    private void registerEvents() {
        PluginManager pluginManager = getServer().getPluginManager();
        //player
        pluginManager.registerEvents(new PlayerInteractEntity(), this);
        pluginManager.registerEvents(new PlayerMove(), this);
        pluginManager.registerEvents(new PlayerDeath(this), this);

    }

}
