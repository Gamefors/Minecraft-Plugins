package com.gmf.miner.commands;

import com.gmf.miner.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setSkillCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        Main.miningSkill = Integer.parseInt(strings[0]);
        p.sendMessage("Your minings skill is now: " + Main.miningSkill);
        return false;
    }
}
