package com.Blackveiled.Diablic.Events;

import com.Blackveiled.Diablic.Chat.ChatChannel;
import com.Blackveiled.Diablic.Diablic;
import com.Blackveiled.Diablic.Entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerOnQuit implements Listener {

    private final Diablic instance;

    public PlayerOnQuit(Diablic instance)   {
        this.instance = instance;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent Event) {
        if(instance.getPlayerManager().containsPlayer(Event.getPlayer().getUniqueId()))   {
            Player PlayerData = instance.getPlayerManager().getPlayer(Event.getPlayer().getUniqueId());

            instance.getPlayerManager().savePlayerToDatabase(Event.getPlayer().getUniqueId());

            ChatChannel global = instance.getChannelManager().getChatChannel(Diablic.global);
            global.removePlayerFromChannel(Event.getPlayer().getUniqueId());
            instance.getChannelManager().updateChatChannel(Diablic.global, global);

            instance.getPlayerManager().removePlayer(Event.getPlayer().getUniqueId());
            
        }
    }
}
