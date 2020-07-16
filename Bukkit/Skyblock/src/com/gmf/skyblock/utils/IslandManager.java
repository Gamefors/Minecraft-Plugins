package com.gmf.skyblock.utils;

import com.gmf.skyblock.main.Main;
import com.gmf.skyblock.objects.Island;
import org.bukkit.entity.Player;

public class IslandManager {

    Player p;
    Island pIsland;

    public IslandManager(Player p, Island pIsland){
        this.p = p;
        this.pIsland = pIsland;
    }

    public void teleportPlayerToIsland(){
        this.p.teleport(this.pIsland.spawn);
        this.p.sendMessage(Main.prefix + " Your have been teleported to your island.");
    }

    public void resetIsland(){
        //maybe detect all blocks in this range an remove them
        //or just create new mysql entry for user wiht new island at new coordinates
    }

}
