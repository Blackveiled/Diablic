package com.Blackveiled.Diablic.Entity;

public class PlayerState {

    private boolean idle = true;
    private boolean combat = false;
    private boolean stunned = false;
    private boolean frozen = false;
    private boolean casting = false;
    private boolean interacting = false;

    private long combatTime = 0;

    // Stun Times
    private long stunTime = 0;
    private long stunDuration = 0;

    // Frozen Times
    private long frozenTime = 0;
    private long frozenDuration = 0;

    // Casting Times
    private long castTime = 0;
    private long castDuration = 0;

    // Interacting Times
    private long interactTime = 0;
    private long interactDuration = 0;

    public boolean isIsle() { return idle; }
    public boolean inCombat()   { return combat; }
    public boolean isStunned()  { return stunned; }
    public boolean isFrozen()   { return frozen; }
    public boolean isCasting()  { return casting; }
    public boolean isInteracting()  { return interacting; }
    public long getCombatTime() { return combatTime; }
    public long getStunTime()   { return stunTime; }
    public long getStunDuration()   { return stunDuration; }
    public long getFrozenTime() { return frozenTime; }
    public long getFrozenDuration() { return frozenDuration; }
    public long getCastTime()   { return castTime; }
    public long getCastDuration()   { return castDuration; }
    public long getInteractTime()   { return interactTime; }
    public long getInteractDuration()   { return interactDuration; }
}
