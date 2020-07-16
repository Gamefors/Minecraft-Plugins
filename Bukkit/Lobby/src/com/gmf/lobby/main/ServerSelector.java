package com.gmf.lobby.main;

import com.gmf.lobby.commands.BuildCommand;
import com.gmf.lobby.utils.ItemBuilder;
import com.google.common.io.ByteArrayDataInput;
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
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class ServerSelector implements Listener, PluginMessageListener {

    Main plugin;

    HashMap<String, ItemStack> server = new HashMap<>();

    public ServerSelector(Main plugin){
        this.plugin = plugin;
        this.plugin.getServer().getMessenger().registerIncomingPluginChannel(this.plugin, "my:lobby", this);
    }

    @Override
    public void onPluginMessageReceived(String channel, Player p, byte[] bytes) {
        if (!channel.equalsIgnoreCase("my:lobby")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(bytes);
        String subChannel = in.readUTF();
        if (subChannel.equalsIgnoreCase("serverstatus")) {
            String server = in.readUTF();
            String status = in.readUTF();
            //p.sendMessage("server: " + server + " status: " + status);
            if(this.server.containsKey(server)){
                if(status.equals("true")){
                    this.server.replace(server, new ItemBuilder(Material.GREEN_STAINED_GLASS).setDisplayName("§b§6" + server + " §2[Online]").toItemStack());
                }else{
                    this.server.replace(server, new ItemBuilder(Material.RED_STAINED_GLASS).setDisplayName("§b§6" + server + " §4[Offline]").toItemStack());
                }
            }else{
                if(status.equals("true")){
                    this.server.put(server, new ItemBuilder(Material.GREEN_STAINED_GLASS).setDisplayName("§b§6" + server + " §2[Online]").toItemStack());
                }else{
                    this.server.put(server, new ItemBuilder(Material.RED_STAINED_GLASS).setDisplayName("§b§6" + server + " §4[Offline]").toItemStack());
                }
            }
        }
    }

    @EventHandler
    public void playerInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack selectedItem = p.getInventory().getItemInMainHand();
        if(selectedItem.hasItemMeta()){
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (selectedItem.getItemMeta().getDisplayName().equals(Main.itemList.get(0).getItemMeta().getDisplayName())) {
                    Inventory pInv = Bukkit.createInventory(null, 9, "Server selector");
                    this.server.forEach((s, itemStack) -> pInv.addItem(itemStack));
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
            this.server.forEach((s, itemStack) -> {
                if (itemStack.getItemMeta().getDisplayName().equals(e.getCurrentItem().getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                    ByteArrayOutputStream b = new ByteArrayOutputStream();
                    DataOutputStream out = new DataOutputStream(b);
                    String serverName = s;
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
            });

        }else{
            if(!BuildCommand.builders.contains(e.getWhoClicked().getName())){
                e.setCancelled(true);
            }
        }
    }

}
