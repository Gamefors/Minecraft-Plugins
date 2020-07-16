package com.gmf.miner.events;

import com.gmf.miner.main.Main;
import jdk.internal.util.xml.impl.ReaderUTF8;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collection;
import java.util.HashMap;

public class Mining implements Listener {

    HashMap<Material, Integer> miningMaterials = new HashMap<Material, Integer>();

    Long oreRespawnTime = 80L; //20L 1sec

    Main plugin;

    public Mining(Main plugin){
        this.plugin = plugin;
        miningMaterials.put(Material.COAL_ORE, 0);
        miningMaterials.put(Material.IRON_ORE, 50);
        miningMaterials.put(Material.GOLD_ORE, 100);
        miningMaterials.put(Material.DIAMOND_ORE, 200);
        miningMaterials.put(Material.REDSTONE_ORE, 225);
        miningMaterials.put(Material.LAPIS_ORE, 250);
        miningMaterials.put(Material.EMERALD_ORE, 300);
        miningMaterials.put(Material.NETHER_QUARTZ_ORE, 400);
        miningMaterials.put(Material.NETHER_GOLD_ORE, 600);




    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @EventHandler
    public void on(BlockBreakEvent e) {
        e.setDropItems(false);
        Player p = e.getPlayer();
        Block b = e.getBlock();
        BlockData bD = b.getBlockData();
        int miningSkill = Main.miningSkill;

        if(b.getBlockData().getMaterial() == Material.BLACK_STAINED_GLASS){
            e.setCancelled(true);
            return;
        }

        if(miningMaterials.containsKey(b.getBlockData().getMaterial())){

            if(p.getInventory().getItemInMainHand().getType() == Material.DIAMOND_PICKAXE){
                if(miningSkill >= miningMaterials.get(b.getBlockData().getMaterial())){
                    Collection<ItemStack> drops = b.getDrops();
                    drops.forEach(itemStack -> {

                        if(getRandomNumber(0,10) >= 5){
                            int add = (int) Math.floor((Main.miningSkill - miningMaterials.get(b.getBlockData().getMaterial())) * 0.10);
                            if(add != 0){
                                itemStack.setAmount(itemStack.getAmount() + add);
                                p.sendMessage("§l§9You gained " + add + " extra " + itemStack.getType().name() + ".");
                            }
                        }
                        p.getInventory().addItem(itemStack);
                    });

                    int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                        @Override
                        public void run() {

                            Location loc4 = b.getLocation();
                            loc4.setX(loc4.getX() + 1);
                            loc4.setY(loc4.getY() + 1);
                            p.getWorld().spawnParticle(Particle.ASH,loc4,1);

                            Location loc5 = b.getLocation();
                            loc5.setZ(loc5.getZ() + 1);
                            loc5.setY(loc5.getY() + 1);
                            p.getWorld().spawnParticle(Particle.ASH,loc5,1);

                            Location loc6 = b.getLocation();
                            loc6.setX(loc6.getX() + 1);
                            loc6.setZ(loc6.getZ() + 1);
                            loc6.setY(loc6.getY() + 1);
                            p.getWorld().spawnParticle(Particle.ASH,loc6,1);

                            Location loc7 = b.getLocation();
                            loc7.setY(loc7.getY() + 1);
                            p.getWorld().spawnParticle(Particle.ASH,loc7,1);

                        }
                    }, 0L, 1L); //0 Tick initial delay, 20 Tick (1 Second) between repeats

                    this.plugin.getServer().getScheduler().runTaskLater(this.plugin, new Runnable() {
                        @Override
                        public void run() {
                            p.getWorld().getBlockAt(b.getLocation()).setBlockData(bD);
                            Bukkit.getScheduler().cancelTask(taskId);
                        }
                    }, oreRespawnTime);

                    Main.miningSkill++;

                    TextComponent tC = new TextComponent("Your mining skill increased by +1");
                    tC.setColor(ChatColor.BLUE);
                    tC.setBold(true);
                    p.sendMessage("§l§9Your mining skill increased by +1.");
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, tC);
                }else{
                    e.setCancelled(true);
                    p.sendMessage("§4Your mining level is to low!");
                }
            }else{
                e.setCancelled(true);
                p.sendMessage("§4You dont have a pickaxe to mine ores.");
            }
        }
    }
}
