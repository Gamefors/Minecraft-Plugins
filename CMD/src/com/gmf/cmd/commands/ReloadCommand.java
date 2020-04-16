package com.gmf.cmd.commands;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ReloadCommand implements Listener {

    Server server;

    public ReloadCommand(Server server){
        this.server = server;
    }

    @EventHandler
    public boolean playerCommandPreprocessEvent(PlayerCommandPreprocessEvent e) {
        String cmd = e.getMessage().split(" ")[0].replace("/", "").toLowerCase();
        Player p = e.getPlayer();

        if (cmd.equals("reload") || cmd.equals("rl")) {
            if(p.hasPermission("cmd.reload")){
                e.setCancelled(true);
                p.sendMessage("§cstarted reloading...");
                this.server.reload();
                this.server.reloadData();
                this.server.reloadWhitelist();
                p.sendMessage("§afinished reloading.");
            }
        }
        return true;
    }

}
