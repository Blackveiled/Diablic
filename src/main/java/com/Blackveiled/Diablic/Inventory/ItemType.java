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

}