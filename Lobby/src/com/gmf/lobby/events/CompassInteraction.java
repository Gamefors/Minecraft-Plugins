package com.gmf.lobby.events;

import com.gmf.lobby.commands.BuildCommand;
import com.gmf.lobby.main.Main;
import com.gmf.lobby.utils.ItemBuilder;
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

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompassInteraction implements Listener {

    Main plugin;

    List<ItemStack> compassItemList = new ArrayList<ItemStack>();

    public CompassInteraction(Main plugin){
        this.plugin = plugin;
        fillCompassItemList();
    }

    private void fillCompassItemList() {
        compassItemList.add(new ItemBuilder(Material.GRASS_BLOCK).setDisplayName("§b§6Gost91").toItemStack());
        compassItemList.add(new ItemBuilder(Material.FEATHER).setDisplayName("§b§6Skyblock").toItemStack());
        compassItemList.add(new ItemBuilder(Material.DIAMOND).setDisplayName("§b§6Creative").toItemStack());
        compassItemList.add(new ItemBuilder(Material.MAGENTA_WOOL).setDisplayName("§b§6Livestream").toItemStack());
    }

    @EventHandler
    public void playerInteractEvent(PlayerInteractEvent e) {

        Player p = e.getPlayer();
        ItemStack selectedItem = p.getInventory().getItemInMainHand();
        if(selectedItem.hasItemMeta()){
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

                if (selectedItem.getItemMeta().getDisplayName().equals(Main.itemList.get(0).getItemMeta().getDisplayName())) {

                    Inventory pInv = Bukkit.createInventory(null, 9, "§b§6Server selector");

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
                try {
                    out.writeUTF("Connect");
                    out.writeUTF(e.getCurrentItem().getItemMeta().getDisplayName().substring(4));
                } catch (IOException eee) {
                    p.sendMessage("IOExeption occurred sending you to the server.");
                }
                p.sendPluginMessage(this.plugin, "BungeeCord", b.toByteArray());
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
