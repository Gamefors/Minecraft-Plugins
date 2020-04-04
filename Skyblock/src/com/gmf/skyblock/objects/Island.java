package com.gmf.skyblock.objects;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.UUID;

public class Island {

    public int id;
    public UUID owner;
    public Location spawn;

    public Island(String id_, String owner_, String[] location_)
    {
        id = Integer.parseInt(id_);
        owner = UUID.fromString(owner_);
        spawn = new Location(Bukkit.getWorld("world"), Double.parseDouble(location_[0]), Double.parseDouble(location_[1]), Double.parseDouble(location_[2]));
    }


}
