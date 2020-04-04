package com.gmf.lobby.events.player;

        import org.bukkit.Bukkit;
        import org.bukkit.Material;
        import org.bukkit.entity.Player;
        import org.bukkit.event.EventHandler;
        import org.bukkit.event.Listener;
        import org.bukkit.event.block.Action;
        import org.bukkit.event.player.PlayerInteractEvent;
        import org.bukkit.inventory.Inventory;
        import org.bukkit.inventory.ItemStack;
        import org.bukkit.inventory.meta.ItemMeta;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.UUID;

public class PlayerInteract implements Listener {

    @EventHandler
    public void playerInteractEvent(PlayerInteractEvent e) {

        Player p = e.getPlayer();
        ItemStack selectedItem = p.getInventory().getItemInMainHand();

        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

            if (selectedItem.getType() == Material.COMPASS) {

                ItemStack creativeSend = new ItemStack(Material.DIAMOND);
                ItemStack gost91Send = new ItemStack(Material.GRASS_BLOCK);
                ItemStack skyblockSend = new ItemStack(Material.FEATHER);
                ItemStack livestreamSend = new ItemStack(Material.MAGENTA_WOOL);


                ItemMeta creativeSendItemMeta = creativeSend.getItemMeta();
                ItemMeta gost91SendItemMeta = gost91Send.getItemMeta();
                ItemMeta skyblockSendItemMeta = skyblockSend.getItemMeta();
                ItemMeta livestreamSendItemMeta = livestreamSend.getItemMeta();


                List<String> creativeSendLore = new ArrayList<String>();
                List<String> gost91SendLore = new ArrayList<String>();
                List<String> skyblockSendLore = new ArrayList<String>();
                List<String> livestreamSendLore = new ArrayList<String>();


                creativeSendLore.add("Sends you to the creative world.");
                gost91SendLore.add("Sends you to the Gost91 world.");
                skyblockSendLore.add("Sends you to the Skyblock world.");
                livestreamSendLore.add("Sends you to the performancebrot livestream world.");

                creativeSendItemMeta.setLore(creativeSendLore);
                gost91SendItemMeta.setLore(gost91SendLore);
                skyblockSendItemMeta.setLore(skyblockSendLore);
                livestreamSendItemMeta.setLore(livestreamSendLore);

                creativeSendItemMeta.setDisplayName("§6Creative Server");
                gost91SendItemMeta.setDisplayName("§6Gost91 Server");
                skyblockSendItemMeta.setDisplayName("§6Skyblock Server");
                livestreamSendItemMeta.setDisplayName("§dperformancebrot §6Livestream Server");

                creativeSend.setItemMeta(creativeSendItemMeta);
                gost91Send.setItemMeta(gost91SendItemMeta);
                skyblockSend.setItemMeta(skyblockSendItemMeta);
                livestreamSend.setItemMeta(livestreamSendItemMeta);

                Inventory lobbyInventory = Bukkit.createInventory(null, 9, "Lobby");

                lobbyInventory.setItem(0, gost91Send);
                lobbyInventory.setItem(1, creativeSend);
                lobbyInventory.setItem(2, skyblockSend);
                lobbyInventory.setItem(6, livestreamSend);

                p.openInventory(lobbyInventory);

            }else{
                e.setCancelled(true);
            }

        }else{
            if(!p.getUniqueId().equals(UUID.fromString("dfc5d199-b370-40d7-ba45-d675a1055ebe"))){
                e.setCancelled(true);
            }

        }

    }

}