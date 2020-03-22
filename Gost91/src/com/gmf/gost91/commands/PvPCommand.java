package com.gmf.gost91.commands;

import com.gmf.gost91.main.Main;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PvPCommand implements CommandExecutor {

    World world;

    private boolean switched = false;

    public PvPCommand(World world){
        this.world = world;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player)commandSender;
        if(p.isOp()){
            if(switched){
                world.setPVP(false);
                p.sendMessage(Main.pluginPrefix + " §cDisabled §fpvp.");
                switched = false;
            }else{
                world.setPVP(true);
                p.sendMessage(Main.pluginPrefix + " §aEnabled §fpvp.");
                switched = true;
            }
        }else{
            p.sendMessage(Main.pluginPrefix + " You are not a server operator.");
        }
        return true;
    }
}
