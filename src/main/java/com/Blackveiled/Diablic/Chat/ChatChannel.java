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
    }
}
