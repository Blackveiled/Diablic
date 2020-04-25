package com.Blackveiled.Diablic.Entity;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

public class PlayerStatistics {

    private int deaths;
    private int mobKills;
    private int bossKills;
    private int playerKills;
    private int dungeonsCompleted;
    private int goldPickedUp;
    private int goldSpent;
    private int goldTraded;

    public PlayerStatistics()   {

    }


    // GETTERS & SETTERS FOR STATISTICS

    public int getDeaths() { return deaths; }   public void setDeaths(int i) { deaths = i; }
    public int getMobKills() { return mobKills; }   public void setMobKills(int i) { mobKills = i; }
    public int getBossKills() { return bossKills; } public void setBossKills(int i) { bossKills = i; }
    public int getPlayerKills() { return playerKills;}  public void setPlayerKills(int i) { playerKills = i; }
    public int getDungeonsCompleted() { return dungeonsCompleted; } public void setDungeonsCompleted(int i) { dungeonsCompleted = i; }
    public int getGoldPickedUp() { return goldPickedUp; }   public void setGoldPickedUp(int i) { goldPickedUp = i; }
    public int getGoldSpent() { return goldSpent; } public void setGoldSpent(int i) { goldSpent = i; }
    public int getGoldTraded() { return goldTraded; } public void setGoldTraded(int i) { goldTraded = i; }
}
