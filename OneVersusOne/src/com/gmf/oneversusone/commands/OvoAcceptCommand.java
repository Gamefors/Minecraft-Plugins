package com.gmf.oneversusone.commands;

import com.gmf.oneversusone.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OvoAcceptCommand implements CommandExecutor {

    Main plugin;

    public OvoAcceptCommand(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        final Player p = (Player)commandSender;
        if(Main.pendingRequest.get(p) == null){
            p.sendMessage(Main.pluginPrefix + " You dont have a pending duel request.");
            return true;
        }
        final Player challenger = Main.pendingRequest.get(p);

        challenger.sendMessage(Main.pluginPrefix + " " + p.getName() + " accepted your duel.");
        p.sendMessage(Main.pluginPrefix + " You accepted the duel against " + challenger.getName() + ".");

        Main.pendingRequest.remove(p);

        Main.fightingPlayers.put(p, "pos1");
        Main.fightingPlayers.put(challenger, "pos2");

        Location loc = new Location(Bukkit.getWorld("world"), Main.xOffArenaPos1, Main.yOffArenaPos1, Main.zOffArenaPos1);
        loc.setYaw(90);
        loc.setPitch(0);
        p.teleport(loc);
        Location loc2 = new Location(Bukkit.getWorld("world"), Main.xOffArenaPos2, Main.yOffArenaPos2, Main.zOffArenaPos2);
        loc2.setYaw(-90);
        loc2.setPitch(0);
        challenger.getPlayer().teleport(loc2);

        Main.duelling = true;

        Bukkit.getWorld("world").setGameRule(GameRule.KEEP_INVENTORY, true);
        Bukkit.getWorld("world").setPVP(true);

        challenger.sendMessage(Main.pluginPrefix + " Duel starting in 5sec...");
        p.sendMessage(Main.pluginPrefix + " Duel starting in 5sec...");

        this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() { public void run() { p.sendMessage(Main.pluginPrefix + " Duel starting in 3sec...");challenger.sendMessage(Main.pluginPrefix + " Duel starting in 3sec..."); } }, 20 * 5);

        this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() { public void run() { p.sendMessage(Main.pluginPrefix + " Duel starting in 2sec..."); challenger.sendMessage(Main.pluginPrefix + " Duel starting in 2sec...");} }, 20 * 8);

        this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() { public void run() { p.sendMessage(Main.pluginPrefix + " Duel starting in 1sec..."); challenger.sendMessage(Main.pluginPrefix + " Duel starting in 1sec...");} }, 20 * 10);

        this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() { public void run() { p.sendMessage(Main.pluginPrefix + " Duel started.");challenger.sendMessage(Main.pluginPrefix + " Duel started."); Main.duelInProgress = true;} }, 20 * 11);

        return true;
    }
}
