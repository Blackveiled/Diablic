package com.Blackveiled.Diablic.Entity;

import com.Blackveiled.Diablic.Chat.ChatChannel;
import com.Blackveiled.Diablic.Diablic;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

import java.io.*;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player {

    private UUID uuid;
    private String name = "";
    private String prefix = "";
    private String suffix = "";
    private ChatColor color = ChatColor.BLUE;
    public String banReason = "";
    private Date LastTimeOnline;
    private EntityAttributes attributes;
    public String lastip;


    public boolean operator = false;
    public boolean banned = false;
    public boolean buildMode = false;

    private InventoryState inventoryState = InventoryState.NONE;
    private Status playerStatus = new Status();


    /***
     * Main Player Data Constructor
     */
    public Player(UUID uuid) {
        
        this.uuid = uuid;
        this.name = Bukkit.getPlayer(uuid).getName();

        if(Diablic.getPlayer(this).isOp()) this.color = ChatColor.RED;

        this.attributes = new EntityAttributes(this.getPlayer());

        String ipStr1 = this.getPlayer().getAddress().getAddress().toString();
        this.lastip = this.getPlayer().getAddress().getAddress().toString().substring(1, ipStr1.length());
    }

    /***
     * Returns the Player's UUID.
     * @return UUID
     */
    public UUID getUniqueId() { return this.uuid; }

    /***
     * Returns the Player's Name.
     * @return String
     */
    public String getName()   { return this.name; }

    /***
     * Gets the Player entity object.
     * @return Player (org.bukkit.entity.Player)
     */
    public org.bukkit.entity.Player getPlayer() { return Bukkit.getPlayer(this.uuid); }


    /***
     * Returns the Player's Prefix.
     * @return String
     */
    public String getPrefix() { return this.prefix; }

    /***
     * Returns the Player's Suffix.
     * @return String
     */
    public String getSuffix() { return this.suffix; }

    /***
     * Sets the Player's Suffix.
     * @param suffix - String
     */
    public void setSuffix(String suffix)    { this.suffix = suffix; }

    /***
     * Sets the Player's Prefix.
     * @param prefix - String
     */
    public void setPrefix(String prefix)    { this.prefix = prefix; }

    /***
     * Returns the player's specified chat color.
     * @return ChatColor (Bukkit API)
     */
    public ChatColor getColor()   { return this.color; }

    /***
     * Sets the player's specified chat color.  (This will be applied to the Prefix / Name / Suffix of the player in chat)
     * @param color - ChatColor (Bukkitnjop[lj'h[n/j;/pol API)
     */
    public void setColor(ChatColor color)   { this.color = color; }

    public String getDisplayName()  {
        String r = "";
        if(this.prefix != "") r = ChatColor.WHITE + "[" + ChatColor.translateAlternateColorCodes('&', this.prefix) + ChatColor.WHITE +"] ";
        r = r + this.color + this.name;
        if(this.suffix != "") r = r + " " + ChatColor.translateAlternateColorCodes('&', this.suffix);
        return r;
    }

    public InventoryState getInventoryState()   {
        return this.inventoryState;
    }

    public void setInventoryState(InventoryState state) {
        this.inventoryState = state;
    }


    /***
     * Saves the player's class in a JSON file for use later.
     */
    public void save()  {
        try {
            JSONStringer stringer = new JSONStringer();
            stringer.object()
                    .key("Name")
                    .value(this.name)
                    .key("Prefix")
                    .value(this.prefix)
                    .key("Suffix")
                    .value(this.suffix)
                    .key("Color")
                    .value(this.color.toString())
                    .key("Banned")
                    .value(this.banned)
                    .key("BanReason")
                    .value(this.banReason)
                    .key("BuildMode")
                    .value(this.buildMode)
                    .endObject();

            File playerFile = new File(Diablic.getUserDataFilePath(this));
            playerFile.createNewFile();

            FileWriter writer = new FileWriter(playerFile);
            writer.write(stringer.toString());
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void load()  {
        File playerFile = new File(Diablic.getUserDataFilePath(this));
        if(!playerFile.exists()) {
            // If the User File does not exist, set up default values for player.
            this.name = Bukkit.getPlayer(uuid).getName();
            if(Bukkit.getPlayer(uuid).isOp()) {
                this.operator = true;
                this.color = ChatColor.RED;
            }
        }   else    {
            try {
                FileReader reader = new FileReader(playerFile);
                JSONTokener tokener = new JSONTokener(reader);
                JSONObject jsonobject = new JSONObject(tokener);
                if(jsonobject.getString("Prefix") != "") { this.prefix = jsonobject.getString("Prefix"); this.prefix = ChatColor.translateAlternateColorCodes('&', this.prefix);}
                if(jsonobject.getString("Suffix") != "") { this.suffix = jsonobject.getString("Suffix"); this.prefix = ChatColor.translateAlternateColorCodes('&', this.suffix);}
                if(jsonobject.has("Banned")) {
                    if (jsonobject.getBoolean("Banned")) {
                        this.banned = true;
                        if (jsonobject.has("BanReason")) {
                            if (jsonobject.getString("BanReason") != null)
                                this.banReason = jsonobject.getString("BanReason");
                            Bukkit.getPlayer(this.uuid).kickPlayer(ChatColor.RED + "Your account has been banned from this server.\nReason: " + this.banReason);
                        }
                    }
                }
                if(jsonobject.has("BuildMode")) this.buildMode = jsonobject.getBoolean("BuildMode");
            }   catch (FileNotFoundException Exception) {
            }
        }
    }
}
