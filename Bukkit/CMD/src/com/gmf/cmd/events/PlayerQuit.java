package com.gmf.cmd.events;

import com.gmf.cmd.commands.VanishCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void playerQuitEvent(PlayerQuitEvent e) {
        if (VanishCommand.vanishedPlayers.contains(e.getPlayer().getName())) {
            e.setQuitMessage("");
        }
    }
}
