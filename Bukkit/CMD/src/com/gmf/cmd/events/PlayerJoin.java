package com.gmf.cmd.events;

import com.gmf.cmd.commands.VanishCommand;
import com.gmf.cmd.commands.createChunkLoaderCommand;
import com.gmf.cmd.main.Main;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    Main plugin;
    public PlayerJoin(Main plugin){
        this.plugin = plugin;
    }

    public void addNPCPacket(EntityPlayer npc, Player player) {
        PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
        connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc)); // "Adds the player data for the client to use when spawning a player" - https://wiki.vg/Protocol#Spawn_Player
        connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc)); // Spawns the NPC for the player client.
        connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360))); // Correct head rotation when spawned in player look direction.
    }

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e) {
        plugin.chunkLoaders.forEach(entityPlayer -> addNPCPacket(entityPlayer,e.getPlayer()));
        if(VanishCommand.vanishedPlayers.contains(e.getPlayer().getName())){
            e.setJoinMessage("");
        }
        for (String pName : VanishCommand.vanishedPlayers) {
            try{
               // e.getPlayer().hidePlayer(this.plugin, Bukkit.getPlayer(pName));
            }catch (IllegalArgumentException ex){

            }
        }
        for (Player p  : Bukkit.getOnlinePlayers()) {
            try{
                if(VanishCommand.vanishedPlayers.contains(e.getPlayer().getName())){
                    try{
                       // p.hidePlayer(this.plugin, e.getPlayer());
                    }catch (IllegalArgumentException ex){

                    }
                }
            }catch (IllegalArgumentException ex){

            }
        }
    }

}
