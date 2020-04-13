package com.Blackveiled.Diablic.Commands;

import com.Blackveiled.Diablic.Commands.Handlers.WarpHandler;
import com.Blackveiled.Diablic.Commands.Utils.PageSorter;
import com.Blackveiled.Diablic.Diablic;
import com.Blackveiled.Diablic.WarpManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;

public class WarpCommand implements CommandExecutor {

    private final Diablic instance;

    public WarpCommand(Diablic instance)   {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (label.equalsIgnoreCase("warp")) {
                if (p.isOp() || p.hasPermission("diablic.command.warp")) {
                    if (args.length > 0) {
                        switch (args[0]) {
                            case "add":
                                if(instance.getWarpManager().hasWarp(args[1])) {
                                    p.sendMessage(ChatColor.RED + "That warp already exists!");
                                    return true;
                                }
                                instance.getWarpManager().addWarp(args[1], p.getWorld().getName(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ());
                                p.sendMessage(ChatColor.GRAY + "Warp " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " has been added!");
                                return true;
                            case "to":
                                if(instance.getWarpManager().hasWarp(args[1])) {
                                    instance.getWarpManager().getWarp(args[1]).teleportPlayer(p.getUniqueId());
                                    return true;
                                }
                                return false;
                            case "remove":
                                if(instance.getWarpManager().hasWarp(args[1])) {
                                    instance.getWarpManager().removeWarp(args[1]);
                                    p.sendMessage(ChatColor.GRAY + "Warp " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " removed!");
                                    return true;
                                }
                            case "list":
                                int page = 1;
                                if(args.length > 0) {
                                    try {
                                        try {
                                            page = Integer.parseInt(args[1]);
                                        } catch (NumberFormatException ex) {
                                            p.sendMessage(ChatColor.RED + "You must provide a valid page number!");
                                            return true;
                                        }
                                    } catch (ArrayIndexOutOfBoundsException ex) {
                                    page = 1;
                                    }
                                }
                                WarpManager manager = instance.getWarpManager();
                                Object[] values2 = manager.values().toArray();

                                PageSorter sorter = new PageSorter(values2, page, 7);

                                p.sendMessage("");
                                p.sendMessage(ChatColor.GRAY + " ============ " + ChatColor.GOLD + "WARP LIST " + ChatColor.GRAY + "============");
                                p.sendMessage(ChatColor.GRAY + "Viewing Page "+ ChatColor.GOLD + page + ChatColor.GRAY + " / " + ChatColor.GOLD + sorter.getTotalPages());
                                p.sendMessage("");

                                for(Object o : sorter.sort())  {
                                    if(o instanceof WarpHandler) {
                                        WarpHandler o2 = (WarpHandler) o;
                                        p.sendMessage(ChatColor.GOLD + o2.getName());
                                    }
                                }

                                p.sendMessage("");
                                p.sendMessage(ChatColor.GRAY + " ============ END OF PAGE ============ ");
                                p.sendMessage("");
                                return true;

                        }
                    }
                }
            }
        }
        return false;
    }
}

