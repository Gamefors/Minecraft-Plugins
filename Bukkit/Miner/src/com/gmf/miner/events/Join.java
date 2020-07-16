package com.gmf.miner.events;

import com.gmf.miner.main.Main;
import com.gmf.miner.utils.ReflectionUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_16_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
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

    public void addNPCPacket(EntityPlayer npc, Player player) {
        PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
        connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc)); // "Adds the player data for the client to use when spawning a player" - https://wiki.vg/Protocol#Spawn_Player
        connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc)); // Spawns the NPC for the player client.
        connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360))); // Correct head rotation when spawned in player look direction.
    }

    @EventHandler
    public void on(PlayerJoinEvent e){
        Main.npcs.forEach(entityPlayer -> addNPCPacket(entityPlayer,e.getPlayer()));
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
