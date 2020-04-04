package com.gmf.skyblock.main;

import com.gmf.skyblock.commands.IsCommand;
import com.gmf.skyblock.commands.VisitCommand;
import com.gmf.skyblock.events.inventory.InventoryClick;
import com.gmf.skyblock.events.player.*;
import com.gmf.skyblock.utils.MysqlHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Main extends JavaPlugin {

    public static String prefix = "§3[Skyblock]§f ";

    public static MysqlHelper mysqlHelper;
    public static List<HashMap<Player,Player>> visitingPlayers = new ArrayList<HashMap<Player,Player>>();

    @Override
    public void onEnable() {
        super.onEnable();
        getServer().getWorld("world").setPVP(false);
        registerEvents();
        try {
            connectToMysql();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        registerCommands();
    }

    private void registerCommands() {
        getCommand("visit").setExecutor(new VisitCommand());
        getCommand("is").setExecutor(new IsCommand());
    }

    public void connectToMysql() throws SQLException, ClassNotFoundException {
        mysqlHelper = new MysqlHelper();
        mysqlHelper.openConnection();
    }

    private void registerEvents() {
        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new InventoryClick(), this);
        pluginManager.registerEvents(new PlayerJoin(this),this);
        pluginManager.registerEvents(new PlayerDropItem(),this);
        pluginManager.registerEvents(new PlayerSwapHandItems(),this);
        pluginManager.registerEvents(new PlayerRespawn(),this);
        pluginManager.registerEvents(new PlayerInteract(),this);
        pluginManager.registerEvents(new PlayerDeath(),this);
        pluginManager.registerEvents(new PlayerMove(),this);
        pluginManager.registerEvents(new PlayerQuit(),this);

    }


}
