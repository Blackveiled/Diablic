package com.Blackveiled.Diablic.Commands;

import com.Blackveiled.Diablic.Chat.ChatChannel;
import com.Blackveiled.Diablic.Diablic;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Unmute implements CommandExecutor {

    private boolean allowCommand = false;
    private boolean isPlayer = false;

    private final Diablic instance;

    public Unmute(Diablic instance)   {
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
            if(label.equalsIgnoreCase("unmute"))  {
                if(Bukkit.getPlayer(args[1]) != null)   {

                    Integer ParsedArgument;
                    // <<< --- Parse Integer For Channel Index --- >>>
                    try {
                        ParsedArgument = Integer.parseInt(args[0]);
                    } catch (NumberFormatException Exception)   {
                        if(isPlayer == true) {
                            Player Sender = (Player) commandSender;
                            Sender.sendMessage(ChatColor.RED + "Invalid channel token.");
                        }
                        return false;
                    }
                    if(instance.getChannelManager().hasChannel(ParsedArgument)) {
                        if(Bukkit.getPlayer(args[1]) != null) {
                            ChatChannel Global = instance.getChannelManager().getChatChannel(ParsedArgument);
                            Global.unmutePlayer(Bukkit.getPlayer(args[1]).getUniqueId());
                            instance.getChannelManager().updateChatChannel(ParsedArgument, Global);
                            return true;
                        }   // <<< -- Else (For when the player is not online -- >>>
                        else    {
                            commandSender.sendMessage(ChatColor.RED + "That player is not online!");
                            return true;
                        }
                    }   // <<< -- Else (For when the channel does not exist) -- >>>
                        else    {
                        commandSender.sendMessage(ChatColor.RED + "Invalid channel token.");
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
