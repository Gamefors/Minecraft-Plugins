package com.gmf.miner.commands;

import com.gmf.miner.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class giveEnchant implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        p.getInventory().addItem(new ItemBuilder(Material.PAPER).setDisplayName("Enchant of Hastefullness").addLoreLine("+20 Haste").toItemStack());
        return false;
    }
}