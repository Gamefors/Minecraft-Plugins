package com.gmf.offlineserverauth.events;

import com.gmf.offlineserverauth.commands.LoginCommand;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class CancelPlayerInteraction implements Listener {

    @EventHandler
    public void playerSwapHandItemsEvent(PlayerSwapHandItemsEvent e){
        if(!LoginCommand.loggedInUsers.contains(e.getPlayer().getName())){
            e.setCancelled(true);
            for(int i=0; i < 100; i ++)
            {
                e.getPlayer().sendMessage("");
            }
            e.getPlayer().sendMessage(ChatColor.RED + "##################################");
            e.getPlayer().sendMessage("");
            e.getPlayer().sendMessage(ChatColor.GOLD + "Please login with /login <password>");
            e.getPlayer().sendMessage("");
            e.getPlayer().sendMessage(ChatColor.RED + "##################################");

        }
    }

    @EventHandler
    public void playerDropItemEvent(PlayerDropItemEvent e){
        if(!LoginCommand.loggedInUsers.contains(e.getPlayer().getName())){
            e.setCancelled(true);
            for(int i=0; i < 100; i ++)
            {
                e.getPlayer().sendMessage("");
            }
            e.getPlayer().sendMessage(ChatColor.RED + "##################################");
            e.getPlayer().sendMessage("");
            e.getPlayer().sendMessage(ChatColor.GOLD + "Please login with /login <password>");
            e.getPlayer().sendMessage("");
            e.getPlayer().sendMessage(ChatColor.RED + "##################################");

        }
    }

    @EventHandler
    public void entityDamageEvent(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            if(!LoginCommand.loggedInUsers.contains(e.getEntity().getName())){
                e.setCancelled(true);
                for(int i=0; i < 100; i ++)
                {
                    e.getEntity().sendMessage("");
                }
                e.getEntity().sendMessage(ChatColor.RED + "##################################");
                e.getEntity().sendMessage("");
                e.getEntity().sendMessage(ChatColor.GOLD + "Please login with /login <password>");
                e.getEntity().sendMessage("");
                e.getEntity().sendMessage(ChatColor.RED + "##################################");
            }
        }
    }

    @EventHandler
    public void entityPickupItemEvent(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player) {
            if(!LoginCommand.loggedInUsers.contains(e.getEntity().getName())){
                e.setCancelled(true);
                for(int i=0; i < 100; i ++)
                {
                    e.getEntity().sendMessage("");
                }
                e.getEntity().sendMessage(ChatColor.RED + "##################################");
                e.getEntity().sendMessage("");
                e.getEntity().sendMessage(ChatColor.GOLD + "Please login with /login <password>");
                e.getEntity().sendMessage("");
                e.getEntity().sendMessage(ChatColor.RED + "##################################");
            }
        }
    }

    @EventHandler
    public void playerInteractEvent(PlayerInteractEvent e) {
        if(!LoginCommand.loggedInUsers.contains(e.getPlayer().getName())){
            e.setCancelled(true);
            e.getPlayer().closeInventory();
            for(int i=0; i < 100; i ++)
            {
                e.getPlayer().sendMessage("");
            }
            e.getPlayer().sendMessage(ChatColor.RED + "##################################");
            e.getPlayer().sendMessage("");
            e.getPlayer().sendMessage(ChatColor.GOLD + "Please login with /login <password>");
            e.getPlayer().sendMessage("");
            e.getPlayer().sendMessage(ChatColor.RED + "##################################");
        }
    }

    @EventHandler
    public void playerMoveEvent(PlayerMoveEvent e) {
        if(!LoginCommand.loggedInUsers.contains(e.getPlayer().getName())){
            if(e.getPlayer().getLocation().getBlockY() < 0) e.getPlayer().teleport(new Location(e.getPlayer().getWorld(), 0.5,1 ,0.5));

        }
    }

}
