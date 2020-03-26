package com.gmf.gost91.commands;

import com.gmf.gost91.main.Main;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PvPCommand implements CommandExecutor {

    World world;

    public PvPCommand(World world){
        this.world = world;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player)commandSender;
        if(p.isOp()){
            if(world.getPVP()){
                world.setPVP(false);
                p.sendMessage(Main.pluginPrefix + " §cDisabled §fpvp.");
            }else{
                world.setPVP(true);
                p.sendMessage(Main.pluginPrefix + " §aEnabled §fpvp.");
            }
        }else{
            p.sendMessage(Main.pluginPrefix + " You are not a server operator.");
        }
        return true;
    }
}
