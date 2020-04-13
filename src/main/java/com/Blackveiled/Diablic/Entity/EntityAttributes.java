package com.Blackveiled.Diablic.Entity;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.UUID;

public class EntityAttributes {

    // Attributes
    private int agility;
    private int strength;
    private int stamina = 100;
    private int spirit;
    private int intellect;

    private int mana;
    private int maxMana;

    // Secondary Attributes
    private int lifePerHit;
    private int lifePerKill;
    private int lifePerHitPercentage;

    private float criticalChance;
    private double criticalDamage;

    private float dodgeChance;
    private float blockChance;

    private UUID entity;

    public EntityAttributes(Entity e)   {
        this.entity = e.getUniqueId();
    }

    public void update()    {
        if(Bukkit.getEntity(this.entity) instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) Bukkit.getEntity(this.entity);
            // Stamina Calculation
            double s = this.stamina * .5;
            entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(s);
        }
    }

}
