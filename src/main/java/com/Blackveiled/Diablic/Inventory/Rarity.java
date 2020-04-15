package com.Blackveiled.Diablic.Inventory;

import org.bukkit.ChatColor;

public enum Rarity {

    JUNK(0), COMMON(1), UNCOMMON(2), RARE(3), EPIC(4), LEGENDARY(5), SET_ITEM(6), MYTHICAL(7), ZENITH(8);

    private int rarity;

    Rarity(int rarity)   {
        this.rarity = rarity;
    }

    @Override
    public String toString()    {
        switch(rarity)  {
            case 0:
                return "Junk";
            case 1:
                return "Common";
            case 2:
                return "Uncommon";
            case 3:
                return "Rare";
            case 4:
                return "Epic";
            case 5:
                return "Legendary";
            case 6:
                return "Set";
            case 7:
                return "Mythical";
            case 8:
                return "Zenith";
        }
        return null; // Shouldnt happen
    }

    /**
     * Global method, used to create a Rarity type from a String.  Useful for conversion to the proper rarity from the
     * MySQL Database.
     * @param s
     * @return
     */
    public static Rarity fromString(String s)   {
        switch(s)   {
            case "Junk":
                return Rarity.JUNK;
            case "Common":
                return Rarity.COMMON;
            case "Uncommon":
                return Rarity.UNCOMMON;
            case "Rare":
                return Rarity.RARE;
            case "Epic":
                return Rarity.EPIC;
            case "Legendary":
                return Rarity.LEGENDARY;
            case "Set":
            case "Set Item":
                return Rarity.SET_ITEM;
            case "Mythical":
                return Rarity.MYTHICAL;
            case "Zenith":
                return Rarity.ZENITH;
        }
        return null;
    }

    public String toStringWithColor()    {
        switch(rarity)  {
            case 0:
                return getColor() + "Junk";
            case 1:
                return getColor() + "Common";
            case 2:
                return getColor() + "Uncommon";
            case 3:
                return getColor() + "Rare";
            case 4:
                return getColor() + "Epic";
            case 5:
                return getColor() + "Legendary";
            case 6:
                return getColor() + "Set";
            case 7:
                return getColor() + "Mythical";
            case 8:
                return getColor() + "Zenith";
        }
        return null; // Shouldnt happen
    }

    public ChatColor getColor()   {
        switch(rarity)  {
            case 0:
                return ChatColor.GRAY;
            case 1:
                return ChatColor.WHITE;
            case 2:
                return ChatColor.GREEN;
            case 3:
                return ChatColor.BLUE;
            case 4:
                return ChatColor.LIGHT_PURPLE;
            case 5:
                return ChatColor.GOLD;
            case 6:
                return ChatColor.YELLOW;
            case 7:
                return ChatColor.AQUA;
            case 8:
                return ChatColor.DARK_RED;
        }
        return null;
    }
}
