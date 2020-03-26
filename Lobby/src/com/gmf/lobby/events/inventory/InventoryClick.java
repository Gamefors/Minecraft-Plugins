package com.gmf.lobby.events.inventory;

import com.gmf.lobby.main.Main;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.io.DataOutputStream;
import java.io.IOException;

public class InventoryClick implements Listener {

    private Main plugin;

    public InventoryClick(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent e){

        if(!(e.getWhoClicked() instanceof Player)){
            e.setCancelled(true);
            return;
        }

        if (e.getInventory() == null) {
            e.setCancelled(true);
            return;
        }

        if (e.getCurrentItem() == null) {
            e.setCancelled(true);
            return;
        }

        Player p = (Player) e.getWhoClicked();

        if(e.getCurrentItem().getType() == Material.GRASS_BLOCK) {
            e.setCancelled(true);
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            try {
                out.writeUTF("Connect");
                out.writeUTF("gost91");
            } catch (IOException eee) {

            }
            p.sendPluginMessage(this.plugin, "BungeeCord", b.toByteArray());
        }else if(e.getCurrentItem().getType() == Material.DIAMOND){
            e.setCancelled(true);
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            try {
                out.writeUTF("Connect");
                out.writeUTF("creative");
            } catch (IOException eee) {

            }
            p.sendPluginMessage(this.plugin, "BungeeCord", b.toByteArray());
        }else{
            e.setCancelled(true);
        }
    }

}
