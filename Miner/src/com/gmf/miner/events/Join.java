package com.gmf.miner.events;

import com.gmf.miner.main.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    Main plugin;

    public Join(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void on(PlayerJoinEvent e){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                TextComponent tC = new TextComponent("Mining Skill: " + Main.miningSkill);
                tC.setColor(ChatColor.BLUE);
                tC.setBold(true);
                e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, tC);
            }

        },0L, 40L);
    }
}
