package com.Blackveiled.Diablic.Inventory;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.UUID;

public class DIAItem {

    // Basic Item Information
    private int index;
    private String name;
    private Rarity rarity;
    private String description;
    private Material material;
    private ItemType type;

    private int maxDurability;

    private ItemFlags itemFlags;
    private ItemModifications itemModifications = null;
    private List<ItemAttribute> attributes;

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
        m.setDisplayName(name);
        return is;
    }

    public int getIndex()   { return index; }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public List<ItemAttribute> getAttributes()  {
        return attributes;
    }

    public Material getMaterial()   { return material; }

}
