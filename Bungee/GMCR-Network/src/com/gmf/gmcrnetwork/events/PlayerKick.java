package com.gmf.gmcrnetwork.events;

import com.gmf.gmcrnetwork.main.Main;
import net.md_5.bungee.api.AbstractReconnectHandler;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;


public class PlayerKick implements Listener {

    Main plugin;

    public PlayerKick(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onServerKickEvent(ServerKickEvent ev) {
        // Protection against NullPointerException

        ServerInfo kickedFrom = null;

        if (ev.getPlayer().getServer() != null) {
            kickedFrom = ev.getPlayer().getServer().getInfo();
        } else if (this.plugin.getProxy().getReconnectHandler() != null) {// If first server and recohandler
            kickedFrom = this.plugin.getProxy().getReconnectHandler().getServer(ev.getPlayer());
        } else { // If first server and no recohandler
            kickedFrom = AbstractReconnectHandler.getForcedHost(ev.getPlayer().getPendingConnection());
            if (kickedFrom == null) // Can still be null if vhost is null...
            {
                kickedFrom = ProxyServer.getInstance().getServerInfo(ev.getPlayer().getPendingConnection().getListener().getDefaultServer());
            }
        }

        ServerInfo kickTo = this.plugin.getProxy().getServerInfo("lobby");

        // Avoid the loop
        if (kickedFrom != null && kickedFrom.equals(kickTo)) {
            return;
        }

        String reason = BaseComponent.toLegacyText(ev.getKickReasonComponent());
        String kMSG = ChatColor.RED + "You were send back to lobby. Reason: %kickmsg%";
        String[] moveMsg = kMSG.replace("%kickmsg%", reason).split("\n");





        ev.setCancelled(true);
        ev.setCancelServer(kickTo);
        if (!(moveMsg.length == 1 && moveMsg[0].equals(""))) {
            for (String line : moveMsg) {
                ev.getPlayer().sendMessage(TextComponent.fromLegacyText(
                        ChatColor.translateAlternateColorCodes('&', line)));
            }
        }

    }
}
