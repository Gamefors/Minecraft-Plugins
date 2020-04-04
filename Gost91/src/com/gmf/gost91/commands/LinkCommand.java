package com.gmf.gost91.commands;

import com.gmf.gost91.main.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LinkCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player)commandSender;

        if(p.getWorld().getName().equals("world")){
            Location pLoc = p.getLocation();
            int y = pLoc.getBlockY();

            int calcX;
            int calcZ;

            double x = pLoc.getBlockX();
            double z = pLoc.getBlockZ();

            if(x == 0){
                calcX = 0;
            }else{
                if(x>0){
                    calcX = (int) Math.floor(x/8);
                }else{
                    calcX = (int) Math.ceil(Math.abs(x)/8);
                    calcX = -calcX;
                    p.sendMessage("calcX:" + x/8);
                }
            }

            if(z == 0){
                calcZ = 0;
            }else{
                if(z>0){
                    calcZ = (int) Math.floor(z/8);
                }else{
                    calcZ = (int) Math.ceil(Math.abs(z)/8);
                    calcZ = -calcZ;
                }
            }


            p.sendMessage(Main.pluginPrefix + " §aOverworld §fsource coordinates: x:"+ (int)x + " y:" + y + " z:" + (int)z + " -> §cNether §fdestination coordinates: x:" + calcX + " y:" + y + " z:" + calcZ + "");

        }else if(p.getWorld().getName().equals("world_nether")) {
            Location pLoc = p.getLocation();
            int y = pLoc.getBlockY();

            int calcX;
            int calcZ;

            double x = pLoc.getBlockX();
            double z = pLoc.getBlockZ();

            if(x == 0){
                calcX = 0;
            }else{
                if(x>0){
                    calcX = (int) (x*8);
                }else{
                    calcX = (int) (x*8);
                }
            }

            if(z == 0){
                calcZ = 0;
            }else{
                if(z>0){
                    calcZ = (int) (z*8);
                }else{
                    calcZ = (int) (z*8);
                }
            }


            p.sendMessage(Main.pluginPrefix + " §cNether §fsource coordinates: x: "+ (int)x + " y:" + y + " z:" + (int)z + " -> §aOverworld §fdestination coordinates: x:" + calcX + " y:" + y + " z:" + calcZ + "");
        }else{
            p.sendMessage(Main.pluginPrefix + " This doesnt work in this world.");
        }

        return true;
    }
}
