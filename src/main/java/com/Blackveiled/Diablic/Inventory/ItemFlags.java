package com.Blackveiled.Diablic.Inventory;

public class ItemFlags {

    // Item Flags (can be changed by modifications, etc)
    private boolean indestructible = false;
    private boolean tradeable = true;
    private boolean bindOnPickup = false;

    public boolean isIndestructible()   {
        if(indestructible) return true; return false;
    }

    public boolean isBindOnPickup() {
        if(bindOnPickup) return true; return false;
    }

    public void setBindOnPickup(boolean bop)    {
        this.bindOnPickup = bop;
    }

    public void setIndestructible(boolean indestructible)   {
        this.indestructible = indestructible;
    }
}
