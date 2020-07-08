package com.gmf.miner.events;

import com.gmf.miner.main.Main;
import com.gmf.miner.utils.ReflectionUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.lang.reflect.Field;

public class Join implements Listener {

    Main plugin;

    public Join(Main plugin){
        this.plugin = plugin;
    }

    public static double round(double value, int decimalPoints) {
        double d = Math.pow(10, decimalPoints);
        return Math.round(value * d) / d;
    }
    public static double getTPS() {
        double tps = 0d;
        try {
            Object mc = ReflectionUtils.invokeMethod(Bukkit.getServer(), "getServer", false);
            Field rec = ReflectionUtils.getField(mc, "recentTps", false);
            double[] recentTps = (double[]) rec.get(mc);
            tps = recentTps[0];
        } catch (Throwable t) {// TODO: Fix CraftBukkit TPS bug
        }

        return round(tps, 2);
    }


    @EventHandler
    public void on(PlayerJoinEvent e){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                TextComponent tC = new TextComponent("Mining Skill: " + Main.miningSkill + " TPS: "+ getTPS());
                tC.setColor(ChatColor.BLUE);
                tC.setBold(true);
                e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, tC);

            }

        },0L, 40L);
    }
}
