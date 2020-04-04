package com.gmf.lobby.main;

import com.gmf.lobby.events.entity.EntityDamage;
import com.gmf.lobby.events.entity.EntityPickupItem;
import com.gmf.lobby.events.inventory.InventoryClick;
import com.gmf.lobby.events.player.PlayerDropItem;
import com.gmf.lobby.events.player.PlayerInteract;
import com.gmf.lobby.events.player.PlayerJoin;
import com.gmf.lobby.events.player.PlayerSwapHandItems;
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
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    private void setWorldSettings() {
        World lobbyWorld = server.getWorld("world");
        setGamerules(lobbyWorld);
        lobbyWorld.setPVP(false);
        lobbyWorld.setStorm(false);
        lobbyWorld.setTime(1000);
        lobbyWorld.setWeatherDuration(0);
        lobbyWorld.setDifficulty(Difficulty.PEACEFUL);
    }

    private void setGamerules(World world) {
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setGameRule(GameRule.DO_FIRE_TICK, false);
        world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
        world.setGameRule(GameRule.DO_ENTITY_DROPS, false);
        world.setGameRule(GameRule.DO_PATROL_SPAWNING, false);
        world.setGameRule(GameRule.SHOW_DEATH_MESSAGES, false);
        world.setGameRule(GameRule.DO_INSOMNIA, false);
        world.setGameRule(GameRule.DO_TRADER_SPAWNING,false);
        world.setGameRule(GameRule.FALL_DAMAGE,false);
        world.setGameRule(GameRule.FIRE_DAMAGE,false);
        world.setGameRule(GameRule.MOB_GRIEFING,false);
        world.setGameRule(GameRule.DROWNING_DAMAGE,false);
        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        world.setGameRule(GameRule.DISABLE_ELYTRA_MOVEMENT_CHECK, true);
        world.setGameRule(GameRule.DISABLE_RAIDS, true);
        world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
    }


    private void registerEvents() {
        PluginManager pluginManager = server.getPluginManager();
        //player
        pluginManager.registerEvents(new PlayerDropItem(), this);
        pluginManager.registerEvents(new PlayerInteract(), this);
        pluginManager.registerEvents(new PlayerJoin(), this);
        pluginManager.registerEvents(new PlayerSwapHandItems(), this);
        //entity
        pluginManager.registerEvents(new EntityPickupItem(), this);
        pluginManager.registerEvents(new EntityDamage(),this);
        //inventory
        pluginManager.registerEvents(new InventoryClick(this), this);
    }

}
