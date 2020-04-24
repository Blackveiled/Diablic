package com.Blackveiled.Diablic.Inventory;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class DIAItem {

    // Basic Item Information
    private int index;
    private int itemLevel;
    private int levelRequirement = 0;
    private String name;
    private Rarity rarity;
    private String description;
    private Material material;
    private ItemType type;

    private double maxDurability;

    private ItemFlags itemFlags;
    private final ItemModifications itemModifications = null;
    private final List<ItemAttribute> attributes = new ArrayList();

    // Item Flags

    public DIAItem(int index, String name, Rarity rarity, Material material, ItemType type)   {
        this.index = index; this.name = name; this.material = material; this.type = type; this.rarity = rarity;
    }

    public void setDescription(String description)  {
        this.description = description;
    }

    public ItemStack createItemStack() {
        ItemStack is = new ItemStack(material);
        ItemMeta m = is.getItemMeta();

        m.setDisplayName(rarity.getColor() + name);
        // Generate Lore for Item
        List<String> l = new ArrayList();

        l.add(rarity.toStringWithColor() + " " + type.toString());

        // Implement Attribute Lore (No particular order)
        if(!attributes.isEmpty()) {
            l.add("");
            l.add(ChatColor.YELLOW + "" + ChatColor.UNDERLINE + "Attributes");
            l.add("");
            for (ItemAttribute att : attributes) {
                l.add(att.getAmountString());
            }
        }

        if(levelRequirement > 0) l.add(ChatColor.RESET + "Requires Level "+levelRequirement);
        if(itemModifications != null) { if(maxDurability > 0) l.add(itemModifications.getCurrentDurability() + "/" + maxDurability + " Durability"); }

        m.setLore(l);
        is.setItemMeta(m);

        m.setDisplayName(name);
        return is;
    }

    public void updateItemStack(ItemStack is)    {

    }

    public int getIndex()   { return index; }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public List<ItemAttribute> getAttributes()  {
        return attributes;
    }


    public Material getMaterial()   { return material; }

    public boolean isBroken()   {
        if(itemFlags.isIndestructible()) return false;
        if(itemModifications != null) if(itemModifications.getCurrentDurability() <= 0) return true;
        return false;
    }

}
