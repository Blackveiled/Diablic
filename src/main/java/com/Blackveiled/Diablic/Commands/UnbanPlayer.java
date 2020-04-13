package com.Blackveiled.Diablic.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class UnbanPlayer implements CommandExecutor {

    private boolean allowCommand = false;
    private boolean isPlayer = false;

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
            if(label.equalsIgnoreCase("unban"))  {
                if(Bukkit.getPlayer(args[0]) != null) {
                    }
                }
        }
        return false;
    }
}
