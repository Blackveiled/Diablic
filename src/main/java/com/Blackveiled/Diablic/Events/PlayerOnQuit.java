package com.Blackveiled.Diablic.Events;

import com.Blackveiled.Diablic.Chat.ChatChannel;
import com.Blackveiled.Diablic.Diablic;
import com.Blackveiled.Diablic.Entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerOnQuit implements Listener {

    private final Diablic instance;

    public PlayerOnQuit(Diablic instance)   {
        this.instance = instance;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if(instance.getPlayerManager().containsPlayer(event.getPlayer().getUniqueId()))   {
            event.setQuitMessage(ChatColor.GOLD + event.getPlayer().getName() + ChatColor.GRAY + " has left the realm.  Hell's minions grow weaker.");

            Player PlayerData = instance.getPlayerManager().getPlayer(event.getPlayer().getUniqueId());

            instance.getPlayerManager().savePlayerToDatabase(event.getPlayer().getUniqueId());

            ChatChannel global = instance.getChannelManager().getChatChannel(Diablic.global);
            global.removePlayerFromChannel(event.getPlayer().getUniqueId());
            instance.getChannelManager().updateChatChannel(Diablic.global, global);

            instance.getPlayerManager().removePlayer(event.getPlayer().getUniqueId());
            
        }
    }
}
