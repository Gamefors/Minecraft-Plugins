package com.gmf.gost91.main;
import com.gmf.gost91.commands.*;
import com.gmf.gost91.events.player.PlayerDeath;
import com.gmf.gost91.events.player.PlayerInteract;
import com.gmf.gost91.events.player.PlayerRespawn;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static String prefix = "§9[Gost91]§f";

    @Override
    public void onEnable() {
        super.onEnable();
        registerCommands();
        registerEvents();
        registerOutGoingPluginChannels();
    }

    private void registerEvents() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerInteract(), this);
        pluginManager.registerEvents(new PlayerDeath(), this);
        pluginManager.registerEvents(new PlayerRespawn(this), this);
    }

    private void registerCommands() {
        getCommand("pvp").setExecutor(new PvPCommand(getServer().getWorld("world"), getServer().getWorld("world_nether"), getServer().getWorld("world_the_end")));
        getCommand("nick").setExecutor(new NickCommand());
        getCommand("link").setExecutor(new LinkCommand());
    }

    private void registerOutGoingPluginChannels() {
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

}
