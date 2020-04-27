package com.Blackveiled.Diablic.Chat;

import com.Blackveiled.Diablic.Diablic;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;

public class ChatChannel {

    private final Diablic instance;

    // This is the parent class for all Chat Channels.

    private int ChannelIndex;
    private String channelName;
    private String channelAbbreviation;
    private ChatColor channelColor;
    private UUID channelOwner;
    private List<UUID> channelModerators = new ArrayList();
    private List<UUID> players = new ArrayList();
    private List<UUID> mutedPlayers = new ArrayList();

    // Later
    private List<UUID> invitedPlayers = new ArrayList(); // Invitations to private channels.

    // Chat Channel Flags
    public boolean privateChannel = false;
    public boolean allowSuffix = true;
    public boolean allowPrefix = true;
    public boolean allowColor = true;
    public boolean operatorSpeakOnly = false; // Flag used for Global Announce Channels.


    public ChatChannel(String ChannelName, Diablic instance)  {
        this.channelName = ChannelName;
        this.instance = instance;
    }

    public boolean isChannelOwner(UUID uuid)    {
        if(this.channelOwner == uuid) return true; return false;
    }

    public void setChannelOwner(UUID uuid)  {
        this.channelOwner = uuid;
    }

    public boolean isChannelModerator(UUID uuid)    {
        if(this.channelModerators.contains(uuid))  return true; return false;
    }

    public void addChannelModerator (UUID uuid) {
        if(this.channelModerators.contains(uuid)) return;
        this.channelModerators.add(uuid);
    }

    public void removeChannelModerator (UUID uuid)  {
        if(this.channelModerators.contains(uuid)) this.channelModerators.remove(uuid);
    }

    public String getChannelName()  {
        return this.channelName;
    }

    public void setChannelName(String ChannelName)  {
        this.channelName = ChannelName;
    }

    public void addPlayerToChannel(UUID uuid)   {
        if(this.players.contains(uuid)) return;

        Bukkit.getPlayer(uuid).sendMessage(this.channelColor + "You have joined " + this.channelName + ".");
        this.players.add(uuid);
    }

    public void removePlayerFromChannel(UUID uuid)  {
        if(this.channelModerators.contains(uuid)) this.channelModerators.remove(uuid);
        if(this.players.contains(uuid)) {
            this.players.remove(uuid);

            // -- Send message to player they have been removed from the channel.
            Bukkit.getPlayer(uuid).sendMessage(this.channelColor + "You have left " + this.channelName + ".");
        }
    }

    public ChatColor getChannelColor()  {
        return this.channelColor;
    }

    public void setChannelColor(ChatColor ChannelColor)    {
        this.channelColor = ChannelColor;
    }

    public void mutePlayer(UUID uuid)   {
        if(this.mutedPlayers.contains(uuid)) return;
        this.mutedPlayers.add(uuid);
    }

    public boolean PlayerMuted(UUID uuid)   {
        if(this.mutedPlayers.contains(uuid)) return true;
        return false;
    }

    public void unmutePlayer(UUID uuid) {
        if(this.mutedPlayers.contains(uuid)) this.mutedPlayers.remove(uuid);
    }

    /***
     * Broadcasts a message to players in the channel, with a player's token.
     * @param ChatMessage - The string following the player's name (the chat message).
     * @param UUID -The UUID of the player sending the message.
     */

    // TO DO: Channel Bans
    public void sendMessage(String ChatMessage, UUID UUID)    {
        Player BukkitPlayer = Bukkit.getPlayer(UUID);
        com.Blackveiled.Diablic.Entity.Player VPlayer = instance.getPlayerManager().getPlayer(UUID);

        if(this.PlayerMuted(UUID)) {
            BukkitPlayer.sendMessage(ChatColor.RED + "You cannot speak in this channel at the moment");
            return;
        }

        ListIterator<UUID> PlayerList = this.players.listIterator();

        for(UUID p : players)   {
            Player Player = Bukkit.getPlayer(p);
            if (Player.isOnline()) {
                Player.sendMessage(
                        this.getChannelColor() + "[" +
                                this.getChannelName() + "] " +
                                VPlayer.getDisplayName() + this.getChannelColor() + ": " + ChatColor.WHITE +
                                ChatMessage);
            }
        }

        // ComponentBuilder using the BungeeCord API; use in the future for advanced chat.

        //ComponentBuilder message = new ComponentBuilder().color(switchChatColors(channelColor)).append("[")
        //    .append(channelName).append("] [" + prefix + "] ").color(switchChatColors(VPlayer.getColor())).append(playerName).color(switchChatColors(channelColor)).append(": ")
        //    .color(net.md_5.bungee.api.ChatColor.WHITE).append(": " + ChatMessage).create();
    }

    /**
     * Converts a Bukkit ChatColor to a BungeeCord ChatColor.
     * @param color
     * @return
     */
    public static net.md_5.bungee.api.ChatColor switchChatColors(ChatColor color)  {
        switch(color)   {
            case AQUA:
                return net.md_5.bungee.api.ChatColor.AQUA;
            case BLACK:
                return net.md_5.bungee.api.ChatColor.BLACK;
            case BOLD:
                return net.md_5.bungee.api.ChatColor.BOLD;
            case BLUE:
                return net.md_5.bungee.api.ChatColor.BLUE;
            case DARK_AQUA:
                return net.md_5.bungee.api.ChatColor.DARK_AQUA;
            case DARK_BLUE:
                return net.md_5.bungee.api.ChatColor.DARK_BLUE;
            case DARK_GRAY:
                return net.md_5.bungee.api.ChatColor.DARK_GRAY;
            case DARK_GREEN:
                return net.md_5.bungee.api.ChatColor.DARK_GREEN;
            case DARK_PURPLE:
                return net.md_5.bungee.api.ChatColor.DARK_PURPLE;
            case DARK_RED:
                return net.md_5.bungee.api.ChatColor.DARK_RED;
            case GOLD:
                return net.md_5.bungee.api.ChatColor.GOLD;
            case GRAY:
                return net.md_5.bungee.api.ChatColor.GRAY;
            case GREEN:
                return net.md_5.bungee.api.ChatColor.GREEN;
            case ITALIC:
                return net.md_5.bungee.api.ChatColor.ITALIC;
            case LIGHT_PURPLE:
                return net.md_5.bungee.api.ChatColor.LIGHT_PURPLE;
            case MAGIC:
                return net.md_5.bungee.api.ChatColor.MAGIC;
            case RED:
                return net.md_5.bungee.api.ChatColor.RED;
            case RESET:
                return net.md_5.bungee.api.ChatColor.RESET;
            case STRIKETHROUGH:
                return net.md_5.bungee.api.ChatColor.STRIKETHROUGH;
            case UNDERLINE:
                return net.md_5.bungee.api.ChatColor.UNDERLINE;
            case WHITE:
                return net.md_5.bungee.api.ChatColor.WHITE;
            case YELLOW:
                return net.md_5.bungee.api.ChatColor.YELLOW;

        }
        return null;
    }
}
