package com.Blackveiled.Diablic.Inventory;

public enum ItemType    {

    SWORD(0), AXE(1), PICKAXE(2), HOE(3), SPADE(4), BOW(5), CROSSBOW(6),
    HELMET(7), CHESTPLATE(8), LEGGINGS(9), BOOTS(10), SHIELD(11), WAND(12),
    BLOCK(13), QUEST_ITEM(14), ORE(15), INGOT(16), BOOK(17), GEM(18), VEHICLE(19),
    SEED(20), EGG(21), COSMETIC(22), DECORATION(23), POTION(24), FOOD(25), DYE(26),
    CONSUMABLE(27), MAP(28), REAGENT(29), UTILITY(30), ADMIN(31), ARROW(32), BULLET(33),
    POLEARM(34);

    private int type;

    ItemType(int type)   {
        this.type = type;
    }

    @Override
    public String toString()    {
        switch(type)    {
            case 0:
                return "Sword";
            case 1:
                return "Axe";
            case 2:
                return "Pickaxe";
            case 3:
                return "Hoe";
            case 4:
                return "Spade";
            case 5:
                return "Bow";
            case 6:
                return "Crossbow";
            case 7:
                return "Helmet";
            case 8:
                return "Chestplate";
            case 9:
                return "Leggings";
            case 10:
                return "Boots";
            case 11:
                return "Shield";
            case 12:
                return "Wand";
            case 13:
                return "Block";
            case 14:
                return "Quest Item";
            case 15:
                return "Ore";
            case 16:
                return "Ingot";
            case 17:
                return "Book";
            case 18:
                return "Gem";
            case 19:
                return "Vehicle";
            case 20:
                return "Seed";
            case 21:
                return "Egg";
            case 22:
                return "Cosmetic";
            case 23:
                return "Decoration";
            case 24:
                return "Potion";
            case 25:
                return "Food";
            case 26:
                return "Dye";
            case 27:
                return "Consumable";
            case 28:
                return "Map";
            case 29:
                return "Reagent";
            case 30:
                return "Utility";
            case 31:
                return "Admin";
            case 32:
                return "Arrow";
            case 33:
                return "Bullet";
            case 34:
                return "Polearm";
        }
        return null;
    }

}