package com.gmf.logging.main;

import com.gmf.logging.events.EntityDamageByEntity;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable(){
        super.onEnable();

        getServer().getPluginManager().registerEvents(new EntityDamageByEntity(),this);

    }

}
