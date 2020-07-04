package com.gmf.lobby.events;

import com.gmf.lobby.commands.BuildCommand;
import com.gmf.lobby.main.Main;
import com.gmf.lobby.utils.ItemBuilder;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class CompassInteraction implements Listener{

    Main plugin;

    List<ItemStack> compassItemList = new ArrayList<ItemStack>();

    public CompassInteraction(Main plugin){
        this.plugin = plugin;
        fillCompassItemList();
    }

    private void fillCompassItemList() {
        compassItemList.add(new ItemBuilder(Material.GRASS_BLOCK).setDisplayName("§b§21.16.1 Survival").toItemStack());
    }

    @EventHandler
    public void playerInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack selectedItem = p.getInventory().getItemInMainHand();
        if(selectedItem.hasItemMeta()){
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (selectedItem.getItemMeta().getDisplayName().equals(Main.itemList.get(0).getItemMeta().getDisplayName())) {
                    Inventory pInv = Bukkit.createInventory(null, 9, "Server selector");
                    int count = 0;
                    for (ItemStack item :
                            compassItemList) {
                        pInv.setItem(count, item);
                        count++;
                    }
                    p.openInventory(pInv);
                }else{
                    if(!BuildCommand.builders.contains(p.getName())){
                        e.setCancelled(true);
                    }
                }
            }else{
                if(!BuildCommand.builders.contains(p.getName())){
                    e.setCancelled(true);
                }
            }
        }else{
            if(!BuildCommand.builders.contains(p.getName())){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent e){
        if((e.getWhoClicked() instanceof Player) && (e.getCurrentItem() != null)) {
            Player p = (Player) e.getWhoClicked();
            if (compassItemList.contains(e.getCurrentItem())) {
                e.setCancelled(true);
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(b);
                String serverName = "";
                switch (e.getSlot()){
                    case 0:
                        serverName = "1161Survival";
                }
                try {
                    out.writeUTF("Connect");
                    out.writeUTF(serverName);
                    p.sendPluginMessage(this.plugin, "BungeeCord", b.toByteArray());
                } catch (IOException ex) {
                    p.sendMessage(String.valueOf(ex));
                }

            }else{
                if(!BuildCommand.builders.contains(p.getName())){
                    e.setCancelled(true);
                }
            }
        }else{
            if(!BuildCommand.builders.contains(e.getWhoClicked().getName())){
                e.setCancelled(true);
            }
        }
    }
}
