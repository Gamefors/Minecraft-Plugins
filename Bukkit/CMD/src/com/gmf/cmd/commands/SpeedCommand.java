package com.gmf.cmd.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if(p.hasPermission("cmd.speed")){
            if(strings.length >= 1){
                Float speed = null;
                try{
                    speed = Float.parseFloat(strings[0])/10;
                }catch (NumberFormatException ex){

                }
                if(speed != null){
                    if(speed > 1.0 || speed < 0.0){
                        p.sendMessage("§cInvalid speed §7[speed 0-10]§c.");
                    }else{
                        if(p.isFlying()){
                            p.setFlySpeed(speed);
                            p.sendMessage("You flying speed ist now: " + speed*10 + ".");
                        }else{
                            p.setWalkSpeed(speed);
                            p.sendMessage("You walking speed ist now: " + speed*10 + ".");
                        }
                    }

                }else{
                    p.sendMessage("§cInvalid speed §7[speed 0-10]§c.");
                }

            }else{
                p.sendMessage("§c/speed §7[speed]");
            }

        }else{
            p.sendMessage("§cYou are not allowed to do that.");
        }
        return true;
    }
}
