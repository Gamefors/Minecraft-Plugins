package com.gmf.miner.main;

import com.gmf.miner.commands.giveEnchant;
import com.gmf.miner.commands.setSkillCommand;
import com.gmf.miner.events.Enchant;
import com.gmf.miner.events.Join;
import com.gmf.miner.events.Mining;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static  int miningSkill = 0;

    @Override
    public void onEnable(){
        super.onEnable();
        getCommand("setSkill").setExecutor(new setSkillCommand());
        getCommand("giveEnchant").setExecutor(new giveEnchant());
        getServer().getPluginManager().registerEvents(new Mining(this), this);
        getServer().getPluginManager().registerEvents(new Join(this), this);
        getServer().getPluginManager().registerEvents(new Enchant(), this);
    }


}
