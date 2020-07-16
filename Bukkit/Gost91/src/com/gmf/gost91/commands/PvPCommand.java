package com.gmf.gost91.commands;

import com.gmf.gost91.main.Main;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PvPCommand implements CommandExecutor {

    World world;
    World nether;
    World end;

    public PvPCommand(World world, World nether, World end){
        this.world = world;
        this.nether = nether;
        this.end = end;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player)commandSender;
        if(p.isOp()){
            if(world.getPVP()){
                world.setPVP(false);
                nether.setPVP(false);
                end.setPVP(false);
                p.sendMessage(Main.prefix + " §cDisabled §fpvp.");
            }else{
                world.setPVP(true);
                nether.setPVP(true);
                end.setPVP(true);
                p.sendMessage(Main.prefix + " §aEnabled §fpvp.");
            }
        }else{
            p.sendMessage(Main.prefix + " You are not a server operator.");
        }
        return true;
    }
}
