package com.Blackveiled.Diablic.Commands;

import com.Blackveiled.Diablic.Diablic;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class BanPlayer implements CommandExecutor {

    private boolean allowCommand = false;
    private boolean isPlayer = false;

    private final Diablic instance;

    public BanPlayer(Diablic instance)   {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(commandSender instanceof Player) {
            if(commandSender.isOp())   {
                this.allowCommand = true;
                this.isPlayer = true;
            }
            else {
                return false;
            }
        }
        if(commandSender instanceof ConsoleCommandSender)   {
            this.allowCommand = true;
            this.isPlayer = false;
        }
        if(this.allowCommand)   {
            if(label.equalsIgnoreCase("ban"))  {
                if(Bukkit.getPlayer(args[0]) != null) {
                    Player player = Bukkit.getPlayer(args[0]);
                    if (!player.isOnline()) {
                        commandSender.sendMessage(ChatColor.RED + "That user is not online!");
                        return true;
                    }
                    if(!(commandSender instanceof ConsoleCommandSender)) {
                        if (player.isOp()) {
                            commandSender.sendMessage(ChatColor.RED + "You cannot ban this player!");
                            return true;
                        }
                    }
                    if (instance.getPlayerManager().hasPlayer(player.getUniqueId())) {
                        com.Blackveiled.Diablic.Entity.Player playerdata = instance.getPlayerManager().getPlayer(player.getUniqueId());
                        playerdata.banned = true;
                        String banreason = "";
                        for (int i = 1; i < args.length; i++) {
                            banreason = banreason + " " + args[i];
                        }
                        playerdata.banReason = banreason;
                        playerdata.save();
                        instance.getPlayerManager().removePlayer(player.getUniqueId());
                        player.kickPlayer(ChatColor.RED + "Your account has been banned from this server.\nReason: "+playerdata.banReason);
                    }
                    }
                }
        }
        return false;
    }
}
