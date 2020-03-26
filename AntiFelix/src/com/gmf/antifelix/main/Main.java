package com.gmf.antifelix.main;

import com.gmf.antifelix.events.entity.EntityDamageByEntity;
import com.gmf.antifelix.events.entity.EntityPickupItem;
import com.gmf.antifelix.events.player.PlayerDropItem;
import com.gmf.antifelix.events.player.PlayerInteract;
import com.gmf.antifelix.events.player.PlayerJoin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable(){
        super.onEnable();
        registerEvents();
    }

    private void registerEvents() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new EntityPickupItem(), this);
        pluginManager.registerEvents(new PlayerDropItem(), this);
        pluginManager.registerEvents(new PlayerInteract(), this);
        pluginManager.registerEvents(new PlayerJoin(), this);
        pluginManager.registerEvents(new EntityDamageByEntity(), this);
    }

}
