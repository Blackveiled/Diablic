package com.Blackveiled.Diablic;

import com.Blackveiled.Diablic.Chat.ChannelManager;
import com.Blackveiled.Diablic.Chat.ChatChannel;
import com.Blackveiled.Diablic.Commands.*;
import com.Blackveiled.Diablic.Database.DatabaseManager;
import com.Blackveiled.Diablic.Entity.PlayerManager;
import com.Blackveiled.Diablic.Events.*;
import com.Blackveiled.Diablic.Entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;
import java.sql.SQLException;
import java.util.*;

public class Diablic extends JavaPlugin {

    private Diablic instance = this;

    public Server GameServer;
    public Map<Integer, ChatChannel> ChatChannels = new HashMap();
    public ChannelManager channelManager = new ChannelManager(this);
    public Map<UUID, Player> Players = new HashMap();
    private PlayerManager playerManager = new PlayerManager(this);
    private WarpManager warpManager = new WarpManager(this);
    private static DatabaseManager databaseManager;


    private String host;
    private int port;
    private String user;
    private String password;
    private String schema;

    // Chat Channel ID Constants
    public static int global = 0;
    private static boolean debug = false;

    public Diablic()   {
        this.GameServer = Bukkit.getServer();
    }

    @Override
    public void onEnable()  {
        instance = this;

        this.saveDefaultConfig();

        this.host = getConfig().getString("database.host");
        this.port = getConfig().getInt("database.port");
        this.user = getConfig().getString("database.user");
        this.password = getConfig().getString("database.password");
        this.schema = getConfig().getString("database.schema");

        this.debug = getConfig().getBoolean("settings.debug");

        databaseManager = new DatabaseManager(this, host, port, user, password, schema);

        //Initialize Database Caches (Synchronized)
        try {
            warpManager.loadDatabase();
        } catch (SQLException ex)   {

        }
        finally {


            // Database Initialization

            // Register Events
            getServer().getPluginManager().registerEvents(new PlayerOnJoin(this), this);
            getServer().getPluginManager().registerEvents(new PlayerChatEvent(this), this);
            getServer().getPluginManager().registerEvents(new PlayerOnQuit(this), this);
            getServer().getPluginManager().registerEvents(new BlockProtectionEvent(this), this);
            getServer().getPluginManager().registerEvents(new WeatherPrevent(), this);
            getServer().getPluginManager().registerEvents(new PlayerInventoryEvent(this), this);

            // Register Commands
            getServer().getPluginCommand("mute").setExecutor(new Mute(this));
            getServer().getPluginCommand("unmute").setExecutor(new Unmute(this));
            getServer().getPluginCommand("ban").setExecutor(new BanPlayer(this));
            getServer().getPluginCommand("buildmode").setExecutor(new BuildMode(this));
            getServer().getPluginCommand("warp").setExecutor(new WarpCommand(this));
            getServer().getPluginCommand("speed").setExecutor(new SpeedCommand());
            getServer().getPluginCommand("jump").setExecutor(new JumpHeightCommand());
            getServer().getPluginCommand("save").setExecutor(new SaveCommand(this));

            ChatChannel global = channelManager.getChatChannel(Diablic.global);

            channelManager.updateChatChannel(Diablic.global, global);
        }
    }

    @Override
    public void onDisable() {
        getDatabaseManager().getDataSource().close();

        for(Entity e : Bukkit.getOnlinePlayers())   {
            org.bukkit.entity.Player p = (org.bukkit.entity.Player) e;
            p.kickPlayer(ChatColor.RED + "Server Reload.  Test phase requires re-connection.  Sorry for the inconvenience.");
        }
    }

    public PlayerManager getPlayerManager()    {
        return this.playerManager;
    }

    public ChannelManager getChannelManager() { return this.channelManager; }

    public WarpManager getWarpManager() { return this.warpManager; }

    public static String getUserDataFilePath(Player Player) { return "plugins/Diablic/Players/" + Player.getUniqueId().toString() + ".user"; }

    public static String getWarpDataFilePath() { return "plugins/Diablic/Warps/WarpList.dat"; }

    public static String getWarpDataDirectory() { return "plugins/Diablic/Warps/"; }

    public static org.bukkit.entity.Player getPlayer(Player player) { if(Bukkit.getPlayer(player.getUniqueId()) != null) return Bukkit.getPlayer(player.getUniqueId()); return null; }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public static boolean debugMode()   {
        return Diablic.debug;
    }
}
