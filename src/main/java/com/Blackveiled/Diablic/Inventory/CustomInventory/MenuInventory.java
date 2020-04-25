package com.Blackveiled.Diablic.Inventory.CustomInventory;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;

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

    @EventHandler
    public void onClick(InventoryClickEvent e)  {

    }

    @EventHandler
    public void onMoveItem(InventoryMoveItemEvent e)    {

    }
}
