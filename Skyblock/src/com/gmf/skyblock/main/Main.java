package com.gmf.skyblock.main;

import com.gmf.skyblock.commands.IsCommand;
import com.gmf.skyblock.commands.VisitCommand;
import com.gmf.skyblock.events.inventory.InventoryClick;
import com.gmf.skyblock.events.player.*;
import com.gmf.skyblock.utils.ItemBuilder;
import com.gmf.skyblock.utils.MysqlHelper;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Main extends JavaPlugin {

    public static String prefix = "§3[Skyblock]§f";

    public static MysqlHelper mysqlHelper;
    public static List<HashMap<Player,Player>> visitingPlayers = new ArrayList<HashMap<Player,Player>>();

    public static List<ItemStack> itemList = new ArrayList<ItemStack>();

    @Override
    public void onEnable() {
        super.onEnable();
        try {
            connectToMysql();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        fillItemList();
        getServer().getWorld("world").setPVP(false);
        registerEvents();

        registerCommands();
    }

    private void fillItemList() {
        itemList.add(new ItemBuilder(Material.NETHER_STAR).setDisplayName("§b§6Skyblock Menu §7(Right click)").addEnchant(Enchantment.ARROW_FIRE,1).addItemFlags(ItemFlag.HIDE_ENCHANTS).toItemStack());
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
