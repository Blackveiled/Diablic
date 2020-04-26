package com.Blackveiled.Diablic.Entity;

import com.Blackveiled.Diablic.Inventory.Attribute;
import com.Blackveiled.Diablic.Inventory.AttributeType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerAttributes {

    // Player Status Attributes
    private double health = 0;
    private int energy = 0;

    // Player Level & Other Stuff
    private int level = 1;
    private int currentXp = 0;

    // Player Base Attributes
    private List<Attribute> attributes = new ArrayList();

    private List<PlayerState> stateList = new ArrayList();

    /**
     * Initializes the base attributes for the player.
     */
    public PlayerAttributes()   {
        // Initialize Primary Attributes
        attributes.add(new Attribute(AttributeType.STRENGTH, 5));
        attributes.add(new Attribute(AttributeType.AGILITY, 5));
        attributes.add(new Attribute(AttributeType.INTELLECT, 5));
        attributes.add(new Attribute(AttributeType.STAMINA, 10));
        attributes.add(new Attribute(AttributeType.SPIRIT, 5));

        // Initialize Defensive Attributes
        attributes.add(new Attribute(AttributeType.ARMOR, 0));
        attributes.add(new Attribute(AttributeType.DODGE_CHANCE, 5));
        attributes.add(new Attribute(AttributeType.BLOCK_CHANCE, 0));
        attributes.add(new Attribute(AttributeType.LIFE_PER_HIT, 0));

        // Initialize Offensive Attributes
        attributes.add(new Attribute(AttributeType.ARMOR_PENETRATION, 0));
        attributes.add(new Attribute(AttributeType.ATTACK_POWER, 0));
        attributes.add(new Attribute(AttributeType.SPELL_POWER, 0));
        attributes.add(new Attribute(AttributeType.HEALING_POWER, 0));
        attributes.add(new Attribute(AttributeType.CRITICAL_CHANCE, 5));
        attributes.add(new Attribute(AttributeType.CRITICAL_DAMAGE, 25));



        // Initialize Resistances
        attributes.add(new Attribute(AttributeType.ALL_RESIST, 0));
        attributes.add(new Attribute(AttributeType.PHYSICAL_RESIST, 0));
        attributes.add(new Attribute(AttributeType.FIRE_RESIST, 0));
        attributes.add(new Attribute(AttributeType.FROST_RESIST, 0));
        attributes.add(new Attribute(AttributeType.HOLY_RESIST, 0));
        attributes.add(new Attribute(AttributeType.SHADOW_RESIST, 0));
        attributes.add(new Attribute(AttributeType.LIGHTNING_RESIST, 0));
        attributes.add(new Attribute(AttributeType.POISON_RESIST, 0));

        health = getStaminaHealthAmount();
        energy = getIntellectEnergyAmount();
    }

    /**
     * Converts the current stamina attribute count to health.  This can be used to calculate the max health for the player.
     * @return
     */
    public double getStaminaHealthAmount() {
        for(Attribute a : attributes)   {
            if(a.getType().equals(AttributeType.STAMINA))   {
                return a.getAmount() * 0.5;
            }
        }
        return 0;
    }

    public int getIntellectEnergyAmount()   {
        for(Attribute a: attributes)    {
            if(a.getType().equals(AttributeType.INTELLECT)) {
                return a.getAmount();
            }
        }
        return 0;
    }

    public List<Attribute> getAttributes()  { return attributes; }

    public List<PlayerState> getStateList() { return stateList; }

    public int getLevel()   { return level; }
    public int getCurrentXp()   { return currentXp; }


}
