package com.Blackveiled.Diablic.Inventory;

import java.util.UUID;

public class ItemModifications {

    private boolean soulbound = false;
    private UUID soulboundOwner;
    private int currentDurability;

    public ItemModifications()  {

    }

    public boolean isSoulbound()   {
        return soulbound;
    }

    public UUID getSoulboundOwner() {
        return soulboundOwner;
    }

    public int getCurrentDurability()   {
        return currentDurability;
    }

    public void setDurability(int durability)   {
        currentDurability = durability;
    }
}
