package com.gmf.cmd.commands;

import com.gmf.cmd.main.Main;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_16_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R1.CraftServer;
import org.bukkit.craftbukkit.v1_16_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class createChunkLoaderCommand implements CommandExecutor {
    Main plugin;
    int count = 1;
    public createChunkLoaderCommand(Main plugin){
        this.plugin = plugin;
    }

    private void createNPC(Location loc, Player p){
        MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer nmsWorld = ((CraftWorld) Bukkit.getWorld("world")).getHandle();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "§4ChunkLoader#" + count); // Change "playername" to the name the NPC should have, max 16 characters.
        EntityPlayer npc = new EntityPlayer(nmsServer, nmsWorld, gameProfile, new PlayerInteractManager(nmsWorld)); // This will be the EntityPlayer (NPC) we send with the sendNPCPacket method.
        npc.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        plugin.chunkLoaders.add(npc);
        count++;
        plugin.getServer().getOnlinePlayers().forEach(player -> {
            PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc)); // "Adds the player data for the client to use when spawning a player" - https://wiki.vg/Protocol#Spawn_Player
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc)); // Spawns the NPC for the player client.
            connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360))); // Correct head rotation when spawned in player look direction.
        });
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if(p.hasPermission("cmd.createCL")){
            createNPC(p.getLocation(), p);
            p.sendMessage("Chunkloader created.");
        }
        return true;
    }
}
