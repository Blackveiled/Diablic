package com.Blackveiled.Diablic.Commands;

import com.Blackveiled.Diablic.Diablic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class BuildMode implements CommandExecutor {

    private boolean allowCommand = false;
    private boolean isPlayer = false;

    private final Diablic instance;

    public BuildMode(Diablic instance)   {
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
            return false;
        }
        if(this.allowCommand)   {
            if(label.equalsIgnoreCase("buildmode"))  {
                Player player = (Player) commandSender;
                if(instance.getPlayerManager().containsPlayer(player.getUniqueId()))  {
                    com.Blackveiled.Diablic.Entity.Player playerData = instance.getPlayerManager().getPlayer(player.getUniqueId());
                    if(playerData.buildMode) { playerData.buildMode = false; player.sendMessage("Build mode disabled."); return true; }
                    if(!playerData.buildMode) { playerData.buildMode = true; player.sendMessage("Build mode enabled."); return true; }
                }
            }
        }
        return false;
    }
}
