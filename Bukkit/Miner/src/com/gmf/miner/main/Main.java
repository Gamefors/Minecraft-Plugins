package com.gmf.miner.main;

import com.gmf.miner.commands.giveEnchant;
import com.gmf.miner.commands.setSkillCommand;
import com.gmf.miner.events.Enchant;
import com.gmf.miner.events.Join;
import com.gmf.miner.events.Mining;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_16_R1.EntityPlayer;
import net.minecraft.server.v1_16_R1.MinecraftServer;
import net.minecraft.server.v1_16_R1.PlayerInteractManager;
import net.minecraft.server.v1_16_R1.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R1.CraftServer;
import org.bukkit.craftbukkit.v1_16_R1.CraftWorld;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main extends JavaPlugin {

    public static  int miningSkill = 0;

    public static List<EntityPlayer> npcs = new ArrayList<>();

    @Override
    public void onEnable(){
        super.onEnable();
        getCommand("setSkill").setExecutor(new setSkillCommand());
        getCommand("giveEnchant").setExecutor(new giveEnchant());
        getServer().getPluginManager().registerEvents(new Mining(this), this);
        getServer().getPluginManager().registerEvents(new Join(this), this);
        getServer().getPluginManager().registerEvents(new Enchant(), this);

        MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer nmsWorld = ((CraftWorld) Bukkit.getWorld("world")).getHandle(); // Change "world" to the world the NPC should be spawned in.
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "Vendor"); // Change "playername" to the name the NPC should have, max 16 characters.
        EntityPlayer npc = new EntityPlayer(nmsServer, nmsWorld, gameProfile, new PlayerInteractManager(nmsWorld)); // This will be the EntityPlayer (NPC) we send with the sendNPCPacket method.
        Location loc = new Location(getServer().getWorld("world"), 8,99,34);
        npc.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        npcs.add(npc);
    }


}
