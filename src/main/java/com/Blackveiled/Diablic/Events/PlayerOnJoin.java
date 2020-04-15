package com.Blackveiled.Diablic.Events;

import com.Blackveiled.Diablic.Chat.ChatChannel;
import com.Blackveiled.Diablic.Diablic;
import com.Blackveiled.Diablic.Entity.Player;
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
            ItemStack is = new ItemStack(Material.COMPASS);
            ItemMeta m = is.getItemMeta();
            m.setDisplayName(ChatColor.RESET + "Game Menu");
            m.setLocalizedName(ChatColor.RESET + "Game Menu");
            is.setItemMeta(m);
            Player.getInventory().setItem(8, is);

            instance.getPlayerManager().loadPlayerFromDatabase(event.getPlayer().getUniqueId());

        }
    }
}
