package com.Blackveiled.Diablic.Events;

import com.Blackveiled.Diablic.Chat.ChatChannel;
import com.Blackveiled.Diablic.Diablic;
import com.Blackveiled.Diablic.Entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class EventPreventer implements Listener {

    private final Diablic instance;

    public EventPreventer(Diablic instance)   {
        this.instance = instance;
    }

    @EventHandler
    public void onPlayerQuit(CreatureSpawnEvent Event) {
        CreatureSpawnEvent.SpawnReason r = Event.getSpawnReason();
        if(r.equals(CreatureSpawnEvent.SpawnReason.NATURAL))    {
            Event.setCancelled(true);
        }
        if(r.equals(CreatureSpawnEvent.SpawnReason.CUSTOM)) {
            Event.setCancelled(false);
        }
    }

}
