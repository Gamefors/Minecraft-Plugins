package com.gamefors.gmf.main;
import com.gamefors.gmf.events.*;
import com.gamefors.gmf.commands.commandGm;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static String prefix = "§8[§aGMF§8]§f";
    public static String errorPrefix = "§8[§4ERROR§8]§f";

    @Override
    public void onEnable(){
        registerEvents();
        registerCommands();
    }

    private void registerCommands() {
        this.getCommand("gm").setExecutor(new commandGm());
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new eventPlayerJoin(),this);
        pm.registerEvents(new eventPlayerDropItem(),this);
        pm.registerEvents(new eventPlayerInteract(),this);
    }

}
