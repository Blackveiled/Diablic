package com.Blackveiled.Diablic.Events;

import com.Blackveiled.Diablic.Diablic;
import com.Blackveiled.Diablic.Entity.InventoryState;
import com.Blackveiled.Diablic.Entity.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.InventoryHolder;

public class PlayerInventoryEvent implements Listener {

    private Diablic instance;

    public PlayerInventoryEvent(Diablic instance)   {
        this.instance = instance;
    }

    @EventHandler
    public void PlayerInventoryEvent(InventoryOpenEvent e)  {
        HandlerList h = e.getHandlers();
        Inventory i = e.getInventory();
        Player p = (Player) e.getPlayer();
        if(i.getType().equals(InventoryType.PLAYER))    {
            PlayerManager pm = instance.getPlayerManager();
            if(pm.hasPlayer(p.getUniqueId()))   {
                com.Blackveiled.Diablic.Entity.Player pd = pm.getPlayer(p.getUniqueId());
                if(pd.getInventoryState().equals(InventoryState.NONE))  {
                    return;
                }
                if(pd.getInventoryState().equals(InventoryState.MENU))  {
                    return;
                }
            }   else    {
                e.setCancelled(true);
            }
        }
        if(i.getType().equals(InventoryType.CREATIVE))  {

        }

        // -- Disable Following Inventory Types -- \\
        if(i.getType().equals(InventoryType.ENCHANTING) || i.getType().equals(InventoryType.ANVIL) || i.getType().equals(InventoryType.CARTOGRAPHY))    {
            if(Diablic.debugMode()) {
                p.sendMessage(ChatColor.RED + "[Debug] " + i.getType().toString() + " disabled!");
            }
            e.setCancelled(true);
        }
    }

    /**
     * This EventHandler will reverse the Player's Inventory State back to NONE after closing out menus.
     * @param e
     */
    @EventHandler
    public void PlayerInventoryCloseEvent(InventoryCloseEvent e)  {
        HandlerList h = e.getHandlers();
        Inventory i = e.getInventory();
        Player p = (Player) e.getPlayer();
        if(i.getType().equals(InventoryType.PLAYER))    {
            PlayerManager pm = instance.getPlayerManager();
            if(pm.hasPlayer(p.getUniqueId()))   {
                com.Blackveiled.Diablic.Entity.Player pd = pm.getPlayer(p.getUniqueId());
                if(pd.getInventoryState().equals(InventoryState.NONE))  {
                    return;
                }
                if(pd.getInventoryState().equals(InventoryState.MENU))  {
                    pd.setInventoryState(InventoryState.NONE);
                    pm.updatePlayer(pd);
                    return;
                }
            }   else    {

            }
        }
        if(i.getType().equals(InventoryType.CREATIVE))  {

        }
    }

    /**
     *
     *
     * @param e
     */
    @EventHandler
    public void PlayerUseItemEvent(PlayerInteractEvent e)    {
        Player p = e.getPlayer();
        PlayerManager pm = instance.getPlayerManager();
        if(pm.hasPlayer(p.getUniqueId()))   {
            com.Blackveiled.Diablic.Entity.Player pd = pm.getPlayer(p.getUniqueId());
            String str = ChatColor.stripColor(e.getItem().getItemMeta().getDisplayName());
            // Access Game Menu Inventory Screen
            if(str.equalsIgnoreCase("Game Menu"))   {
                pd.setInventoryState(InventoryState.MENU);
                pm.updatePlayer(pd);
                p.openInventory(p.getInventory());
                p.sendMessage(ChatColor.GRAY + "Opening Game Menu");
                //e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void PlayerInventoryInteractEvent(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        PlayerManager pm = instance.getPlayerManager();
        if(e.getInventory().getType().equals(InventoryType.PLAYER)) {
            p.sendMessage("Slot: " + e.getSlot());
        }
    }
}
