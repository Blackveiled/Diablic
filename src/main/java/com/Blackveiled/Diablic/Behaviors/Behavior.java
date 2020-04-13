package com.Blackveiled.Diablic.Behaviors;

import org.bukkit.entity.LivingEntity;

public class Behavior {

    public enum BehaviorType    {

        ATTRIBUTE(0), // Attributes are attribute modifiers.
        BUFF(1), // Buffs are Timed or Permanent behaviors attached; they give entity modifications over a period or permanently.
        CLICK_RESPONSE(2), // Click Response is how an entity may respond to a click (i.e. right click on an entity)
        SPAWN(3); // A periodic behavior which spawns units near the entity that has behavior.

        private int behaviorType;

        BehaviorType(int i)  {
            this.behaviorType = i;
        }

        public BehaviorType getBehaviorType()   { return this; }

        @Override
        public String toString() { switch(behaviorType) { case 0: return "Attribute"; case 1: return "Buff"; case 2: return "Click Response"; case 3: return "Spawn"; } return null; }

        public boolean equals(BehaviorType behaviorType) { if(behaviorType.toString() == this.toString()) return true; return false; }

    }

    private boolean behaviorCompleted = false;

    private BehaviorType behaviorType;
    private int count = 1; // The amount of behaviors currently stacked (some behaviors may stack on top of eachother for multiplied effect. (Default val: 1 (There must always be at least
    // one count.  The behavior will destroy itself if it reaches zero.
    private int maxCount = -1; // The maximum amount of behaviors that may be stacked. (Default val: -1 (No limit))
    private float durationMax = -1; // The maximum duration the behavior can last before it is destroyed.  (Default val: -1 (Permanent))
    private float currentDuration = 0;
    private float periodDuration; // The duration (in ticks) for 1 period cycle.
    private float currentPeriod = 0;
    private int periodCount; // The current count of period cycles completed.
    private int periodCountMax; // The maximum amount of periods cycled allowed.
    private Effect periodInitialEffect;  // The Effect applied upon creation of the Behavior.
    private Effect periodEffect; // The Effect applied for each period count.
    private Effect periodFinalEffect; // The Effect applied on the final period.
    private LivingEntity entity; // The entity which the behavior is applied to.

    public Behavior()   {

    }

    /**
     * Creates a Behavior.  This constructor is useful for adding timed buffs to units.
     *
     * @param c Count - The amount of "stacks" of this behavior to be applied.
     * @param d - Duration - The duration this behavior lasts.
     * @param ef - The initial effect applied by this behavior.
     * @param en - The LivingEntity this behavior is to be applied to.
     * @param bh - The behavior type for this selected behavior.
     */
    public Behavior(int c, float d, Effect ef, LivingEntity en, BehaviorType bh) {
        this.count = c; this.durationMax = d; this.periodInitialEffect = ef; this.entity = en; this.behaviorType = bh;
    }

    public LivingEntity getEntity() {
        return this.entity;
    }

    public boolean isPermanent() { if(this.durationMax <= -1) return true; return false; }

    public void setCount(int i) { if(i >= 1) this.count = i; if(i <= 0) this.behaviorCompleted = true; }

    /**
     * If this behavior is set to completed, or it has fulfilled its maximum duration of life, this method will return true.
     * This method will also return true if the "count" field is less than 1. (Zero stacks)
     * @return
     */
    public boolean durationCompleted() {
        if(this.behaviorCompleted) return true;
        if(durationMax >= 0) if(currentDuration >= durationMax) return true;
        if(count <= 0) return true;
        return false;
    }

    /**
     * This method will apply the behavior and the initial effect to the LivingEntity class attached to the behavior.
     * @return
     */
    public boolean applyBehavior()  {
        return false;
    }

}
