package com.gmf.oneversusone.events.entity;

import com.gmf.oneversusone.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerInteractEntity implements Listener {

    @EventHandler
    public void playerInteractEntityEvent(PlayerInteractEntityEvent e){
        EquipmentSlot eS = e.getHand();
        if (eS.equals(EquipmentSlot.HAND)) {
            if(e.getRightClicked() instanceof Player){
                Player rightClickedPlayer = (Player) e.getRightClicked();
                Player p = e.getPlayer();
                if(p.isSneaking()){
                    if(!Main.duelInProgress){
                        //TODO: use text component to click in chat to accept or decline (wait for update of api)
                        if(Main.pendingRequest.containsKey(rightClickedPlayer)){
                            p.sendMessage(Main.pluginPrefix + " There is already a request pending for " + rightClickedPlayer.getName() + ".");
                        }else{
                            p.sendMessage(Main.pluginPrefix + " You challenge " + rightClickedPlayer.getName() + " to a §aduel §fwait for him to §aaccept§f.");
                            rightClickedPlayer.sendMessage(Main.pluginPrefix + " " + p.getName() + " challenged you to a §aduel §ftype either §a/OvOaccept §for §4/OvOdecline§f.");
                            Main.pendingRequest.put(rightClickedPlayer,p);
                        }
                    }else{
                        p.sendMessage(Main.pluginPrefix + " There is already a duel in progress.");
                    }
                }else{
                    e.setCancelled(true);
                }
            }else{
                e.setCancelled(true);
            }
        }
    }



}
