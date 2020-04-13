package com.Blackveiled.Diablic.Commands.Handlers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.UUID;

public class WarpHandler {

    private String name;

    private Location location;

    public WarpHandler(String name, String world, double x, double y, double z) {
        this.name = name;

        this.location = new Location(Bukkit.getWorld(world), x, y, z);
    }

    public WarpHandler getWarpHandler() {
        return this;
    }

    public World getWorld() {
        return this.location.getWorld();
    }

    public void teleportPlayer(UUID uuid)    {
        Player p = Bukkit.getPlayer(uuid);
        p.teleport(location);
        p.sendMessage(ChatColor.GRAY + "You have warped to " + ChatColor.GOLD + name + ChatColor.GRAY + "!");
    }

    public double getX()    {
        return this.location.getX();
    }

    public double getY()    {
        return this.location.getY();
    }

    public double getZ()    {
        return this.location.getZ();
    }

    public String getName() {
        return this.name;
    }

}
