package com.Blackveiled.Diablic.Inventory;

import org.bukkit.ChatColor;

public class Attribute {

    private AttributeType type;
    private int amount;

    public Attribute(AttributeType type, int amount)  {
        this.type = type;
        this.amount = amount;
    }

    public int getAmount()  {
        return this.amount;
    }

    public void setAmount(int amount)   {
        this.amount = amount;
    }

    public AttributeType getType()  {
        return this.type;
    }

    public String getAmountString() {
        String amt;
        amt = "+";
        if(amount < 0) amt = "-";
        return ChatColor.RESET + amt + getAmount() + " " + type.toString();
    }
}
