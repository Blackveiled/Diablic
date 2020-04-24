package com.Blackveiled.Diablic.Inventory;

import org.bukkit.ChatColor;

public class ItemAttribute {

    public enum AttributeType {

        // Primary Attributes
        AGILITY(0), STRENGTH(1), INTELLECT(2), SPIRIT(3), STAMINA(4),

        //Secondary Attributes
        CRITICAL_CHANCE(5), CRITICAL_DAMAGE(6),
        DODGE_CHANCE(7), ARMOR(8), SPELL_DAMAGE(9),
        ATTACK_POWER(10), ARMOR_PENETRATION(11), LIFE_PER_HIT(12),
        HEALING_POWER(13), BLOCK_CHANCE(14),

        // Resistances
        ALL_RESIST(15), PHYSICAL_RESIST(16), FIRE_RESIST(17),
        HOLY_RESIST(18), SHADOW_RESIST(19), POISON_RESIST(20),
        LIGHTNING_RESIST(21),  FROST_RESIST(22);

        private int type;

        AttributeType(int type) {
            this.type = type;
        }

        @Override
        public String toString()    {
            switch(type)    {
                case 0:
                    return "Agility";
                case 1:
                    return "Strength";
                case 2:
                    return "Intellect";
                case 3:
                    return "Spirit";
                case 4:
                    return "Stamina";
                case 5:
                    return "Critical Chance";
                case 6:
                    return "Critical Damage";
                case 7:
                    return "Dodge Chance";
                case 8:
                    return "Armor";
                case 9:
                    return "Spell Damage";
                case 10:
                    return "Attack Power";
            }
            return null;
        }

        public static AttributeType fromString(String str) {
            switch(str) {
                case "Agility":
                    return AttributeType.AGILITY;
                case "Strength":
                    return AttributeType.STRENGTH;
                case "Intellect":
                    return AttributeType.INTELLECT;
                case "Spirit":
                    return AttributeType.SPIRIT;
                case "Stamina":
                    return AttributeType.STAMINA;
                case "Critical Damage":
                    return AttributeType.CRITICAL_DAMAGE;
                case "Critical Chance":
                    return AttributeType.CRITICAL_CHANCE;
            }
            return null;
        }

    }

    private AttributeType type;
    private int amount;

    public ItemAttribute(AttributeType type, int amount)  {
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
