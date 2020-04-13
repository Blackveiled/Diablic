package com.Blackveiled.Diablic.Inventory;

public class ItemAttribute {

    public enum AttributeType {

        // Primary Attributes
        AGILITY(0), STRENGTH(1), INTELLECT(2), SPIRIT(3), STAMINA(4),

        //Secondary Attributes
        CRITICAL_CHANCE(5), CRITICAL_DAMAGE(6),
        DODGE_CHANCE(7), ARMOR(8), SPELL_DAMAGE(9),
        ATTACK_POWER(10);

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
}
