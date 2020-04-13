package com.Blackveiled.Diablic.Events;

import com.Blackveiled.Diablic.Chat.ChatChannel;
import com.Blackveiled.Diablic.Diablic;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

public class PlayerChatEvent implements Listener {

    private final Diablic instance;

    public PlayerChatEvent(Diablic instance)   {
        this.instance = instance;
    }

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent event) {
        UUID Player = event.getPlayer().getUniqueId();
        String Message = event.getMessage();
        event.setCancelled(true);
        // Add actions
        ChatChannel Channel = instance.getChannelManager().getChatChannel(Diablic.global);
        if(!Channel.PlayerMuted(Player)) {
            Channel.sendMessage(Message, Player);
        }   else    {
            Bukkit.getPlayer(Player).sendMessage(ChatColor.RED + "You cannot speak in this channel at the moment.");
        }
    }
}
