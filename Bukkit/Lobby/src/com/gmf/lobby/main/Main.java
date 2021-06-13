package com.gmf.lobby.main;

import com.gmf.lobby.commands.BuildCommand;
import com.gmf.lobby.events.*;
import com.gmf.lobby.items.ServerSelector;
import com.gmf.lobby.utils.ItemBuilder;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {

    public static List<ItemStack> itemList = new ArrayList<ItemStack>();

    @Override
    public void onEnable(){
        super.onEnable();
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        PluginManager pm = getServer().getPluginManager();
        registerPermissions(pm);
        fillItemList();
        setWorldSettings();
        registerEvents(pm);
        registerCommands();
    }

    private void registerCommands() {
        getCommand("build").setExecutor(new BuildCommand());
    }

    private void registerPermissions(PluginManager pm) {
        pm.addPermission(new Permission("lobby.build"));
    }

    private void fillItemList() {
        itemList.add(new ItemBuilder(Material.COMPASS).setDisplayName("§b§6Server selector §7(Right click)").addEnchant(Enchantment.ARROW_FIRE,1).addItemFlags(ItemFlag.HIDE_ENCHANTS).toItemStack());
    }


    private void setWorldSettings() {
        World lobbyWorld = getServer().getWorld("world");
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

    private void registerEvents(PluginManager pm) {
        pm.registerEvents(new CancelPlayerInteraction(), this);
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new PlayerQuit(),this);
        pm.registerEvents(new ServerSelector(this, pm), this);
    }
}
