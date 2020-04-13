package com.Blackveiled.Diablic.Events;

import com.Blackveiled.Diablic.Diablic;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityExplodeEvent;

/***
 *
 * This event class offers protection for the game map.
 *
 * Events:
 *      BlockBreakEvent (Player)
 *      BlockBurnEvent
 *      BlockIgniteEvent
 *      BlockSpreadEvent (Prevents the Spread of Fire)
 *
 *
 */

public class BlockProtectionEvent implements Listener {

    private final Diablic instance;

    public BlockProtectionEvent(Diablic instance)   {
        this.instance = instance;
    }

    @EventHandler
    public void onPlayerBuild(BlockBreakEvent event)   {
        Player player = event.getPlayer();
        if(instance.getPlayerManager().hasPlayer(event.getPlayer().getUniqueId()))  {
            com.Blackveiled.Diablic.Entity.Player playerData = instance.getPlayerManager().getPlayer(event.getPlayer().getUniqueId());
            if(!playerData.buildMode) {event.setCancelled(true); player.sendMessage(ChatColor.RED + "You cannot break blocks here."); return;}
        }
    }

    @EventHandler
    public void onPlayerBuild(BlockPlaceEvent event)   {
        Player player = event.getPlayer();
        if(instance.getPlayerManager().containsPlayer(event.getPlayer().getUniqueId()))  {
            com.Blackveiled.Diablic.Entity.Player playerData = instance.getPlayerManager().getPlayer(event.getPlayer().getUniqueId());
            if(!playerData.buildMode) {event.setCancelled(true); player.sendMessage(ChatColor.RED + "You cannot build here."); return;}
        }
    }

    @EventHandler
    public void onBlockIgniteEvent(BlockIgniteEvent event)  { event.setCancelled(true); }

    @EventHandler
    public void onBlockBurnEvent(BlockBurnEvent event)  { event.setCancelled(true); }

    @EventHandler
    public void onBlockSpreadEvent(BlockSpreadEvent event)  {
        if(event.getSource().getType().equals(Material.FIRE))   {
            event.setCancelled(true);

        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event)   { event.setCancelled(true); }
}
