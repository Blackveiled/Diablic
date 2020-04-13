package com.Blackveiled.Diablic.Commands;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args)    {
        if(label.equalsIgnoreCase("speed")) {
            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if (p.isOp()) {
                    if (args != null) {
                        try {
                            float speed = Float.parseFloat(args[0]);
                            if(speed > 100) { p.sendMessage(ChatColor.RED + "You must input a speed of 1-100."); return true; }
                            if(speed < 1) { p.sendMessage(ChatColor.RED + "You must input a speed of 1-100."); return true; }
                            speed = speed / 100;
                            p.setWalkSpeed(speed);
                            p.setFlySpeed(speed);
                            return true;
                        } catch (NumberFormatException ex) {
                            p.sendMessage(ChatColor.RED + "You must input a valid speed.");
                            return true;
                        }
                    }
                }
                return false;
            }
            return false;
        }
        return false;
    }
}
