package com.Blackveiled.Diablic.Chat;

import com.Blackveiled.Diablic.Diablic;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;

public class ChannelManager {

    private final Diablic instance;

    private Map<Integer, ChatChannel> ChatChannels = new HashMap();

    public ChannelManager(Diablic instance) {
        this.instance = instance;

        ChatChannel Global = new ChatChannel("Global", this.instance);
        Global.setChannelColor(ChatColor.GRAY);
        this.ChatChannels.put(Diablic.global, Global);
    }

    public ChatChannel getChatChannel(int i)    {
        if(ChatChannels.containsKey(i)) return ChatChannels.get(i); return null;
    }

    public boolean addChatChannel(int i, ChatChannel c)    {
        if(ChatChannels.containsKey(i)) return false;
        ChatChannels.put(i, c);
        return true;
    }

    public boolean removeChatChannel(int i) {
        if(ChatChannels.containsKey(i)) { ChatChannels.remove(i); return true; }
        return false;
    }

    public void updateChatChannel(int i, ChatChannel c)  {
        ChatChannels.put(i, c);
    }

    public boolean hasChannel(int i)    {
        if(ChatChannels.containsKey(i)) return true;
        return false;
    }
}
