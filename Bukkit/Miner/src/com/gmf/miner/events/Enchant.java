package com.gmf.miner.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Enchant implements Listener {

    HashMap<String, String> lastItemPickedUp = new HashMap<>();
    boolean enchanted = false;

    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent e){
        if(enchanted){
            enchanted = false;
            e.setCancelled(true);
            return;
        }
        if((e.getWhoClicked() instanceof Player) && (e.getCurrentItem() != null)) {
            Player p = (Player) e.getWhoClicked();
            ItemStack selectedEnchant = e.getCurrentItem();
            if(selectedEnchant.getType().name().equals("PAPER")) {
                List<String> lore = selectedEnchant.getItemMeta().getLore();
                lastItemPickedUp.put(selectedEnchant.getType().name(),lore.get(0));

            }else if(selectedEnchant.getType().name().equals("NETHERITE_SWORD") && lastItemPickedUp.get("PAPER") != null) {

                ItemMeta iM = e.getCurrentItem().getItemMeta();
                List<String> lore = new ArrayList<>();
                lore.add(lastItemPickedUp.get("PAPER"));
                iM.setLore(lore);
                e.getCurrentItem().setItemMeta(iM);
                p.sendMessage("Enchanted Netherite Sword Succsefully!");

                e.setCancelled(true);
                lastItemPickedUp.clear();
                enchanted = true;
            }else{
                lastItemPickedUp.clear();
            }

        }
    }

}
