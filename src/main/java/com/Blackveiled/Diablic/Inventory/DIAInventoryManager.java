package com.Blackveiled.Diablic.Inventory;

import com.Blackveiled.Diablic.Diablic;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DIAInventoryManager {

    private final Diablic instance;

    private Map<Integer, DIAItem> itemCache = new HashMap();
    private Map<String, Integer> itemNameConversionCache = new HashMap();

    public DIAInventoryManager(Diablic instance)   {
        this.instance = instance;
    }

    public PlayerInventory getInventory(UUID uuid)  {
        return null;
    }

    public DIAItem getItemFromString(String string) {
        int itemIndex = -1;
        if(itemNameConversionCache.containsKey(string)) {
            itemIndex = itemNameConversionCache.get(string);
        }
        if(itemIndex >= 0)  {
            if(itemCache.containsKey(itemIndex)) {
                return itemCache.get(itemIndex);
            }
        }
        return null;
    }

    public DIAItem getItem(int i)   {
        if(itemCache.containsKey(i))    {
            return itemCache.get(i);
        }
        return null;
    }

}
