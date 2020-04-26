package com.Blackveiled.Diablic.Inventory;

import com.Blackveiled.Diablic.Entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class PlayerInventory {

    private DIAItem inventory[] = new DIAItem[40];
    private final Player player;

    public PlayerInventory(final Player p)    {
        this.player = p;
    }

    public DIAItem[] getInventory() { return inventory; }


}
