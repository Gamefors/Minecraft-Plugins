package com.gmf.lobby.main;

import com.gmf.lobby.events.entity.EntityDamage;
import com.gmf.lobby.events.entity.EntityPickupItem;
import com.gmf.lobby.events.inventory.InventoryClick;
import com.gmf.lobby.events.player.PlayerDropItem;
import com.gmf.lobby.events.player.PlayerInteract;
import com.gmf.lobby.events.player.PlayerJoin;
import org.bukkit.*;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    Server server = getServer();

    @Override
    public void onEnable(){
        super.onEnable();
        setWorldSettings();
        registerEvents();
        registerOutGoingPluginChannels();
    }

    private void registerOutGoingPluginChannels() {
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    private void setWorldSettings() {
        World lobbyWorld = server.getWorld("world");
        lobbyWorld.setPVP(false);
        lobbyWorld.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        lobbyWorld.setTime(1000);
        lobbyWorld.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        lobbyWorld.setGameRule(GameRule.DISABLE_ELYTRA_MOVEMENT_CHECK, true);
        lobbyWorld.setGameRule(GameRule.DISABLE_RAIDS, true);
        lobbyWorld.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        lobbyWorld.setDifficulty(Difficulty.PEACEFUL);
        lobbyWorld.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        lobbyWorld.setStorm(false);
        lobbyWorld.setWeatherDuration(0);
        lobbyWorld.setGameRule(GameRule.DO_FIRE_TICK, false);
        lobbyWorld.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
        lobbyWorld.setGameRule(GameRule.DO_ENTITY_DROPS, false);
        lobbyWorld.setGameRule(GameRule.DO_PATROL_SPAWNING, false);
        lobbyWorld.setGameRule(GameRule.SHOW_DEATH_MESSAGES, false);
        lobbyWorld.setGameRule(GameRule.DO_INSOMNIA, false);
        lobbyWorld.setGameRule(GameRule.DO_TRADER_SPAWNING,false);
        lobbyWorld.setGameRule(GameRule.FALL_DAMAGE,false);
        lobbyWorld.setGameRule(GameRule.FIRE_DAMAGE,false);
        lobbyWorld.setGameRule(GameRule.MOB_GRIEFING,false);
        lobbyWorld.setGameRule(GameRule.DROWNING_DAMAGE,false);
    }


    private void registerEvents() {
        PluginManager pluginManager = server.getPluginManager();
        //player
        pluginManager.registerEvents(new PlayerDropItem(), this);
        pluginManager.registerEvents(new PlayerInteract(), this);
        pluginManager.registerEvents(new PlayerJoin(), this);
        //entity
        pluginManager.registerEvents(new EntityPickupItem(), this);
        pluginManager.registerEvents(new EntityDamage(),this);
        //inventory
        pluginManager.registerEvents(new InventoryClick(this), this);

    }

}
