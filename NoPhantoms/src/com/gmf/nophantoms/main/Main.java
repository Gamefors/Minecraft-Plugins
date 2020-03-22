package com.gmf.nophantoms.main;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();
        getServer().getWorld("world").setGameRule(GameRule.DO_INSOMNIA, false);
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage("[NoPhantoms] Disabled Phantoms.");
        }
    }
}
