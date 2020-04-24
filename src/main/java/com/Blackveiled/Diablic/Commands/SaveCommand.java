package com.Blackveiled.Diablic.Commands;

import com.Blackveiled.Diablic.Diablic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SaveCommand implements CommandExecutor {

    private final Diablic instance;

    public SaveCommand(Diablic instance)   {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (label.equalsIgnoreCase("save")) {
                if (p.isOp() || p.hasPermission("diablic.command.save")) {

                }
            }
        }
        return false;
    }
}

