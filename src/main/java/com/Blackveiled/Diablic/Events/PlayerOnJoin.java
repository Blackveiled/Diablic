package com.Blackveiled.Diablic.Events;

import com.Blackveiled.Diablic.Chat.ChatChannel;
import com.Blackveiled.Diablic.Diablic;
import com.Blackveiled.Diablic.Entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerOnJoin implements Listener {

    private final Diablic instance;

    public PlayerOnJoin(Diablic instance)   {
        this.instance = instance;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(!instance.getPlayerManager().containsPlayer(event.getPlayer().getUniqueId())) {
            org.bukkit.entity.Player Player = event.getPlayer();
            event.setJoinMessage(ChatColor.GOLD + event.getPlayer().getName() + ChatColor.GRAY + " has entered the realm.  Hell's minions grow stronger.");
            Player.sendMessage(ChatColor.GRAY + "This server is powered by " + ChatColor.GOLD + "Diablic-1.0.1 Alpha" + ChatColor.GRAY + ".");
            Player.sendMessage(ChatColor.GRAY + "=============================================");
            Player.sendMessage("");
            // PlayerJoining.save();

            instance.getPlayerManager().loadPlayerFromDatabase(event.getPlayer().getUniqueId());

        }
    }
}
