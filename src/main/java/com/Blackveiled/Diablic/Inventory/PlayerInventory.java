package com.Blackveiled.Diablic.Inventory;

import com.Blackveiled.Diablic.Entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class PlayerInventory {

    private final List<DIAItem> inventory = new ArrayList(40);
    private final Player player;

    public PlayerInventory(final Player p)    {
        this.player = p;
    }

    public List<DIAItem> getInventory() { return inventory; }

    public DIAItem getItemFromSlot(int slot)    { if(inventory.get(slot) != null) return inventory.get(slot); return null;}


}
