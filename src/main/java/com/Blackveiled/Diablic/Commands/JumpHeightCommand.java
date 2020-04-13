package com.Blackveiled.Diablic.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JumpHeightCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args)    {
        if(label.equalsIgnoreCase("jump")) {
            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if (p.isOp()) {
                    if (args != null) {
                        try {
                            int amp = Integer.parseInt(args[0]);
                            p.removePotionEffect(PotionEffectType.JUMP);
                            p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 9999, amp));
                            return true;
                        } catch (NumberFormatException ex) {
                            p.sendMessage(ChatColor.RED + "You must input a valid amplifier.");
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
