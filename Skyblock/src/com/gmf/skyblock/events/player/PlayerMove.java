package com.gmf.skyblock.events.player;

import com.gmf.skyblock.main.Main;
import com.gmf.skyblock.objects.Island;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.sql.SQLException;
import java.util.HashMap;

public class PlayerMove implements Listener {

    Block lxlt = null;
    Block lxlm = null;
    Block lxlb = null;

    Block lxmt = null;
    Block lxmm = null;
    Block lxmb = null;

    Block lxrt = null;
    Block lxrm = null;
    Block lxrb = null;


    Block lzlt = null;
    Block lzlm = null;
    Block lzlb = null;

    Block lzmt = null;
    Block lzmm = null;
    Block lzmb = null;

    Block lzrt = null;
    Block lzrm = null;
    Block lzrb = null;

    @EventHandler
    public void playerMoveEvent(PlayerMoveEvent e) throws SQLException {

        Player p = e.getPlayer();

        World world = p.getWorld();
        Island pIsland = null;

        for (HashMap<Player, Player> players :
                Main.visitingPlayers) {
            if(players.get(p) != null){
               Player playerThatIsVisited = players.get(p);
               pIsland = Main.mysqlHelper.getIslandByUUID(playerThatIsVisited.getUniqueId());
            }
        }

        if(pIsland == null){
            pIsland = Main.mysqlHelper.getIslandByUUID(p.getUniqueId());
        }

        Location spawn = pIsland.spawn;

        Location currLocation = e.getPlayer().getLocation();


        int diffX = currLocation.getBlockX() - spawn.getBlockX();
        int diffZ = currLocation.getBlockZ() - spawn.getBlockZ();

        if(Math.abs(diffX) > 2500 || Math.abs(diffZ) > 2500){
            if(!p.isOp()){
                p.teleport(spawn);
                p.sendMessage(Main.prefix + " You were outside of your skyblock area!");
            }
        }
        int distanceX;
        int distanceZ;
        if(diffX > 0){
            distanceX = 2500 - diffX;
            distanceZ = 2500 - diffZ;
        }else{
            distanceX = -2500 - diffX;
            distanceZ = -2500 - diffZ;
        }



        Block xlt = world.getBlockAt(currLocation.getBlockX() + distanceX , currLocation.getBlockY() + 2, currLocation.getBlockZ() - 1);
        Block xlm = world.getBlockAt(currLocation.getBlockX() + distanceX , currLocation.getBlockY() + 1, currLocation.getBlockZ() - 1);
        Block xlb = world.getBlockAt(currLocation.getBlockX() + distanceX , currLocation.getBlockY(), currLocation.getBlockZ() - 1);

        Block xmt = world.getBlockAt(currLocation.getBlockX() + distanceX , currLocation.getBlockY() + 2, currLocation.getBlockZ());
        Block xmm = world.getBlockAt(currLocation.getBlockX() + distanceX , currLocation.getBlockY() + 1, currLocation.getBlockZ());
        Block xmb = world.getBlockAt(currLocation.getBlockX() + distanceX , currLocation.getBlockY(), currLocation.getBlockZ());

        Block xrt = world.getBlockAt(currLocation.getBlockX() + distanceX , currLocation.getBlockY() + 2, currLocation.getBlockZ() + 1);
        Block xrm = world.getBlockAt(currLocation.getBlockX() + distanceX , currLocation.getBlockY() + 1, currLocation.getBlockZ() + 1);
        Block xrb = world.getBlockAt(currLocation.getBlockX() + distanceX , currLocation.getBlockY(), currLocation.getBlockZ() + 1);

        Block zlt = world.getBlockAt(currLocation.getBlockX() + 1, currLocation.getBlockY() + 2, currLocation.getBlockZ() + distanceZ);
        Block zlm = world.getBlockAt(currLocation.getBlockX() + 1, currLocation.getBlockY() + 1, currLocation.getBlockZ() + distanceZ);
        Block zlb = world.getBlockAt(currLocation.getBlockX() + 1, currLocation.getBlockY(), currLocation.getBlockZ() + distanceZ);

        Block zmt = world.getBlockAt(currLocation.getBlockX(), currLocation.getBlockY() + 2, currLocation.getBlockZ() + distanceZ);
        Block zmm = world.getBlockAt(currLocation.getBlockX(), currLocation.getBlockY() + 1, currLocation.getBlockZ() + distanceZ);
        Block zmb = world.getBlockAt(currLocation.getBlockX(), currLocation.getBlockY(), currLocation.getBlockZ() + distanceZ);

        Block zrt = world.getBlockAt(currLocation.getBlockX() - 1, currLocation.getBlockY() + 2, currLocation.getBlockZ() + distanceZ);
        Block zrm = world.getBlockAt(currLocation.getBlockX() - 1, currLocation.getBlockY() + 1, currLocation.getBlockZ() + distanceZ);
        Block zrb = world.getBlockAt(currLocation.getBlockX() - 1, currLocation.getBlockY(), currLocation.getBlockZ() + distanceZ);

        if(diffX >= 2495){
            xlt.setType(Material.RED_STAINED_GLASS);
            xlm.setType(Material.RED_STAINED_GLASS);
            xlb.setType(Material.RED_STAINED_GLASS);

            xmt.setType(Material.RED_STAINED_GLASS);
            xmm.setType(Material.RED_STAINED_GLASS);
            xmb.setType(Material.RED_STAINED_GLASS);

            xrt.setType(Material.RED_STAINED_GLASS);
            xrm.setType(Material.RED_STAINED_GLASS);
            xrb.setType(Material.RED_STAINED_GLASS);
        }

        if(diffZ >= 2495){
            zlt.setType(Material.RED_STAINED_GLASS);
            zlm.setType(Material.RED_STAINED_GLASS);
            zlb.setType(Material.RED_STAINED_GLASS);

            zmt.setType(Material.RED_STAINED_GLASS);
            zmm.setType(Material.RED_STAINED_GLASS);
            zmb.setType(Material.RED_STAINED_GLASS);

            zrt.setType(Material.RED_STAINED_GLASS);
            zrm.setType(Material.RED_STAINED_GLASS);
            zrb.setType(Material.RED_STAINED_GLASS);
        }
        if(diffX <= -2495){
            xlt.setType(Material.RED_STAINED_GLASS);
            xlm.setType(Material.RED_STAINED_GLASS);
            xlb.setType(Material.RED_STAINED_GLASS);

            xmt.setType(Material.RED_STAINED_GLASS);
            xmm.setType(Material.RED_STAINED_GLASS);
            xmb.setType(Material.RED_STAINED_GLASS);

            xrt.setType(Material.RED_STAINED_GLASS);
            xrm.setType(Material.RED_STAINED_GLASS);
            xrb.setType(Material.RED_STAINED_GLASS);
        }

        if(diffZ <= -2495){
            zlt.setType(Material.RED_STAINED_GLASS);
            zlm.setType(Material.RED_STAINED_GLASS);
            zlb.setType(Material.RED_STAINED_GLASS);

            zmt.setType(Material.RED_STAINED_GLASS);
            zmm.setType(Material.RED_STAINED_GLASS);
            zmb.setType(Material.RED_STAINED_GLASS);

            zrt.setType(Material.RED_STAINED_GLASS);
            zrm.setType(Material.RED_STAINED_GLASS);
            zrb.setType(Material.RED_STAINED_GLASS);
        }


        if(lxmt != null){
            if (currLocation.getBlockX() + distanceX != lxmt.getX()){
                lxlt.setType(Material.AIR);
                lxlm.setType(Material.AIR);
                lxlb.setType(Material.AIR);
                lxmt.setType(Material.AIR);
                lxmm.setType(Material.AIR);
                lxmb.setType(Material.AIR);
                lxrt.setType(Material.AIR);
                lxrm.setType(Material.AIR);
                lxrb.setType(Material.AIR);
           }
        }

        if(lxmt != null){
            if (currLocation.getBlockY() + 2 != lxmt.getY()){
                lxlt.setType(Material.AIR);
                lxmt.setType(Material.AIR);
                lxrt.setType(Material.AIR);
            }
        }

        if(lxmm != null){
            if (currLocation.getBlockY() + 1 != lxmm.getY()){
                lxlm.setType(Material.AIR);
                lxmm.setType(Material.AIR);
                lxrm.setType(Material.AIR);
            }
        }

        if(lxmb != null){
            if (currLocation.getBlockY() != lxmb.getY()){
                lxlb.setType(Material.AIR);
                lxmb.setType(Material.AIR);
                lxrb.setType(Material.AIR);
            }
        }

        if(lxmt != null){
            if (currLocation.getBlockZ() != lxmt.getZ()){
                lxlt.setType(Material.AIR);
                lxlm.setType(Material.AIR);
                lxlb.setType(Material.AIR);
                lxmt.setType(Material.AIR);
                lxmm.setType(Material.AIR);
                lxmb.setType(Material.AIR);
                lxrt.setType(Material.AIR);
                lxrm.setType(Material.AIR);
                lxrb.setType(Material.AIR);
            }
        }


        if(lzmt != null){
            if (currLocation.getBlockZ() + distanceZ != lzmt.getZ()){
                lzlt.setType(Material.AIR);
                lzlm.setType(Material.AIR);
                lzlb.setType(Material.AIR);
                lzmt.setType(Material.AIR);
                lzmm.setType(Material.AIR);
                lzmb.setType(Material.AIR);
                lzrt.setType(Material.AIR);
                lzrm.setType(Material.AIR);
                lzrb.setType(Material.AIR);
            }
        }

        if(lzmt != null){
            if (currLocation.getBlockY() + 2 != lzmt.getY()){
                lzlt.setType(Material.AIR);
                lzmt.setType(Material.AIR);
                lzrt.setType(Material.AIR);
            }
        }

        if(lzmm != null){
            if (currLocation.getBlockY() + 1 != lzmm.getY()){
                lzlm.setType(Material.AIR);
                lzmm.setType(Material.AIR);
                lzrm.setType(Material.AIR);
            }
        }

        if(lzmb != null){
            if (currLocation.getBlockY() != lzmb.getY()){
                lzlb.setType(Material.AIR);
                lzmb.setType(Material.AIR);
                lzrb.setType(Material.AIR);
            }
        }

        if(lzmt != null){
            if (currLocation.getBlockX() != lzmt.getX()){
                lzlt.setType(Material.AIR);
                lzlm.setType(Material.AIR);
                lzlb.setType(Material.AIR);
                lzmt.setType(Material.AIR);
                lzmm.setType(Material.AIR);
                lzmb.setType(Material.AIR);
                lzrt.setType(Material.AIR);
                lzrm.setType(Material.AIR);
                lzrb.setType(Material.AIR);
            }
        }


        lxlt = xlt;
        lxlm = xlm;
        lxlb = xlb;

        lxmt = xmt;
        lxmm = xmm;
        lxmb = xmb;

        lxrt = xrt;
        lxrm = xrm;
        lxrb = xrb;


        lzlt = zlt;
        lzlm = zlm;
        lzlb = zlb;

        lzmt = zmt;
        lzmm = zmm;
        lzmb = zmb;

        lzrt = zrt;
        lzrm = zrm;
        lzrb = zrb;

    }

}
