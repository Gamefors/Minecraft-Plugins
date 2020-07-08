package com.gmf.miner.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ItemBuilder {

    private ItemStack is;

    public ItemBuilder(Material m){
        this(m, 1);
    }

    public ItemBuilder(ItemStack is){
        this.is=is;
    }

    public ItemBuilder(Material m, int amount){
        is= new ItemStack(m, amount);
    }

    public com.gmf.miner.utils.ItemBuilder setDisplayName(String name){
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(name);
        is.setItemMeta(im);
        return this;
    }

    public com.gmf.miner.utils.ItemBuilder addUnsafeEnchantment(Enchantment ench, int level){
        is.addUnsafeEnchantment(ench, level);
        return this;
    }

    public com.gmf.miner.utils.ItemBuilder removeEnchantment(Enchantment ench){
        is.removeEnchantment(ench);
        return this;
    }

    public com.gmf.miner.utils.ItemBuilder addEnchant(Enchantment ench, int level){
        ItemMeta im = is.getItemMeta();
        im.addEnchant(ench, level, true);
        is.setItemMeta(im);
        return this;
    }

    public com.gmf.miner.utils.ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments){
        is.addEnchantments(enchantments);
        return this;
    }

    public com.gmf.miner.utils.ItemBuilder addItemFlags(ItemFlag itemFlag){
        ItemMeta im = is.getItemMeta();
        im.addItemFlags(itemFlag);
        is.setItemMeta(im);
        return this;
    }

    public com.gmf.miner.utils.ItemBuilder setLore(String... lore){
        ItemMeta im = is.getItemMeta();
        im.setLore(Arrays.asList(lore));
        is.setItemMeta(im);
        return this;
    }

    public com.gmf.miner.utils.ItemBuilder setLore(List<String> lore) {
        ItemMeta im = is.getItemMeta();
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    public com.gmf.miner.utils.ItemBuilder removeLoreLine(String line){
        ItemMeta im = is.getItemMeta();
        List<String> lore = new ArrayList<>(im.getLore());
        if(!lore.contains(line))return this;
        lore.remove(line);
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    public com.gmf.miner.utils.ItemBuilder removeLoreLine(int index){
        ItemMeta im = is.getItemMeta();
        List<String> lore = new ArrayList<>(im.getLore());
        if(index<0||index>lore.size())return this;
        lore.remove(index);
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    public com.gmf.miner.utils.ItemBuilder addLoreLine(String line){
        ItemMeta im = is.getItemMeta();
        List<String> lore = new ArrayList<>();
        if(im.hasLore())lore = new ArrayList<>(im.getLore());
        lore.add(line);
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    public com.gmf.miner.utils.ItemBuilder addLoreLine(String line, int pos){
        ItemMeta im = is.getItemMeta();
        List<String> lore = new ArrayList<>(im.getLore());
        lore.set(pos, line);
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    public com.gmf.miner.utils.ItemBuilder setLeatherArmorColor(Color color){
        try{
            LeatherArmorMeta im = (LeatherArmorMeta)is.getItemMeta();
            im.setColor(color);
            is.setItemMeta(im);
        }catch(ClassCastException expected){}
        return this;
    }

    public ItemStack toItemStack(){
        return is;
    }

}
