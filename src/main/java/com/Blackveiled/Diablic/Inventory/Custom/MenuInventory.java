package com.Blackveiled.Diablic.Inventory.Custom;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class MenuInventory implements InventoryHolder, Listener {


    private final Inventory inventory;

    public MenuInventory()  {
        inventory = Bukkit.createInventory(this, 9, "Game Menu");

        initializeItems();
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public void initializeItems()   {

    }
}
