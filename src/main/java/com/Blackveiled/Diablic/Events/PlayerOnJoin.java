package com.Blackveiled.Diablic.Events;

import com.Blackveiled.Diablic.Diablic;
import com.Blackveiled.Diablic.Inventory.DIAItem;
import com.Blackveiled.Diablic.Inventory.ItemAttribute;
import com.Blackveiled.Diablic.Inventory.ItemType;
import com.Blackveiled.Diablic.Inventory.Rarity;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerOnJoin implements Listener {

    private final Diablic instance;

    public PlayerOnJoin(Diablic instance)   {
        this.instance = instance;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(!instance.getPlayerManager().containsPlayer(event.getPlayer().getUniqueId())) {
            org.bukkit.entity.Player Player = event.getPlayer();
            event.setJoinMessage(ChatColor.GOLD + "" + ChatColor.BOLD + event.getPlayer().getName() + ChatColor.GRAY + " has entered the realm.  Hell's minions grow stronger.");
            Player.sendMessage("");
            Player.sendMessage(ChatColor.GRAY + "This server is powered by " + ChatColor.GOLD + "Diablic-1.0.1 Alpha" + ChatColor.GRAY + ".");
            Player.sendMessage(ChatColor.GRAY + "=============================================");
            Player.sendMessage("");
            // PlayerJoining.save();

            // ITEM INVENTORY TEST SHIT
            DIAItem di = new DIAItem(1, "Game Menu", Rarity.MYTHICAL, Material.COMPASS, ItemType.BLOCK);
            di.getAttributes().add(new ItemAttribute(ItemAttribute.AttributeType.STAMINA, 10));
            di.getAttributes().add(new ItemAttribute(ItemAttribute.AttributeType.ATTACK_POWER, 2));
            di.getAttributes().add(new ItemAttribute(ItemAttribute.AttributeType.CRITICAL_CHANCE, 1));
            ItemStack is = di.createItemStack();
            Player.getInventory().setItem(8, is);

            instance.getPlayerManager().loadPlayerFromDatabase(event.getPlayer().getUniqueId());

        }
    }
}
