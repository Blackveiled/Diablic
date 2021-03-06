package com.Blackveiled.Diablic.Events;

import com.Blackveiled.Diablic.Diablic;
import com.Blackveiled.Diablic.Inventory.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerEvents implements Listener {

    private final Diablic instance;

    public PlayerEvents(Diablic instance)   {
        this.instance = instance;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if(!instance.getPlayerManager().containsPlayer(e.getPlayer().getUniqueId())) {
            org.bukkit.entity.Player Player = e.getPlayer();
            e.setJoinMessage(ChatColor.GOLD + "" + ChatColor.BOLD + e.getPlayer().getName() + ChatColor.GRAY + " has entered the realm.  Hell's minions grow stronger.");
            Player.sendMessage("");
            Player.sendMessage(ChatColor.GRAY + "This server is powered by " + ChatColor.GOLD + "Diablic-1.0.1 Alpha" + ChatColor.GRAY + ".");
            Player.sendMessage(ChatColor.GRAY + "=============================================");
            Player.sendMessage("");
            // PlayerJoining.save();


            instance.getPlayerManager().loadPlayerFromDatabase(e.getPlayer().getUniqueId());

        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e)   {
        Player p = e.getEntity();
        if(instance.getPlayerManager().hasPlayer(p.getUniqueId()))  {
            com.Blackveiled.Diablic.Entity.Player pData = instance.getPlayerManager().getPlayer(p.getUniqueId());
            pData.getStatistics().setDeaths(pData.getStatistics().getDeaths() + 1);
            instance.getPlayerManager().updatePlayer(pData);
            e.setDeathMessage(ChatColor.GOLD + "" + ChatColor.BOLD + p.getName() + ChatColor.GRAY + " has been killed!");
        }
    }
}
