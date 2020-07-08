package com.gmf.cmd.main;

import com.gmf.cmd.commands.*;
import com.gmf.cmd.events.PlayerJoin;
import com.gmf.cmd.events.PlayerQuit;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable(){
        super.onEnable();
        PluginManager pm = getServer().getPluginManager();
        registerPermissions(pm);
        registerEvents(pm);
        registerCommands(pm);
    }

    private void registerPermissions(PluginManager pm) {

        pm.addPermission(new Permission("cmd.gamemode"));
        pm.addPermission(new Permission("cmd.reload"));
        pm.addPermission(new Permission("cmd.speed"));
        pm.addPermission(new Permission("cmd.vanish"));
    }

    private void registerEvents(PluginManager pm) {
        pm.registerEvents(new PlayerJoin(this),this);
        pm.registerEvents(new PlayerQuit(),this);

    }

    private void registerCommands(PluginManager pm) {
        pm.registerEvents(new ReloadCommand(getServer()), this);
        pm.registerEvents(new GamemodeCommand(), this);

        getCommand("vanish").setExecutor(new VanishCommand(this));
        getCommand("speed").setExecutor(new SpeedCommand());
        getCommand("day").setExecutor(new TimeCommand());
        getCommand("night").setExecutor(new TimeCommand());
    }

}
