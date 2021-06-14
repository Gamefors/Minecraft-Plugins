package com.gmf.offlineserverauth.main;


import com.gmf.offlineserverauth.commands.LoginCommand;
import com.gmf.offlineserverauth.events.CancelPlayerInteraction;
import com.gmf.offlineserverauth.events.PlayerJoin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable(){
        super.onEnable();
        PluginManager pm = getServer().getPluginManager();
        registerEvents(pm);
        registerCommands();
    }

    private void registerCommands() {
        getCommand("login").setExecutor(new LoginCommand());
    }

    private void registerEvents(PluginManager pm) {
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new CancelPlayerInteraction(), this);

    }
}
