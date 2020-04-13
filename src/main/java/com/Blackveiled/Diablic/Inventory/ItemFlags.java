package com.Blackveiled.Diablic.Inventory;

public class ItemFlags {

    // Item Flags (can be changed by modifications, etc)
    private boolean indestructible = false;
    private boolean bindOnPickup = false;

    public ItemFlags()  {

    }

    public boolean isIndestructible()   {
        if(indestructible) return true; return false;
    }

    public boolean isBindOnPickup() {
        if(bindOnPickup) return true; return false;
    }
}
