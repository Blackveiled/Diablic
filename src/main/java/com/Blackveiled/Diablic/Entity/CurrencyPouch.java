package com.Blackveiled.Diablic.Entity;

public class CurrencyPouch {

    // Core Currencies
    private int gold;

    // Premium Currencies
    private int platinum;

    /**
     * Initialize Player Currency Pouch.  Default constructor will layout the "base" currency for a player.
     */
    public CurrencyPouch()  {
        gold = 0;
        platinum = 0;
    }

    /**
     * Initialize Player Currency Pouch.  This constructor is used for loading player's currency from the database.
     * @param g Gold
     * @param p Platinum
     */
    public CurrencyPouch(int g, int p)    {
        gold = g;
        platinum = p;
    }

    public void addGold(int a)  {
        gold += a;
    }
    public void subtractGold(int a) {
        gold -= a;
    }
    public void addPlatinum(int a)  {
        platinum += a;
    }
    public void subtractPlatinum(int a) {
        platinum -= a;
    }
    public boolean hasEnoughGold(int a) {
        if(gold >= a) return true;
        return false;
    }
    public boolean hasEnoughPlatinum(int a) {
        if(platinum >= a) return true;
        return false;
    }
    public void setGold(int a)  {
        gold = a;
    }
    public void setPlatinum(int a)  {
        platinum = a;
    }
    public int getGold()   {   return gold;    }
    public int getPlatinum()    {   return platinum;    }

    // --------------------- GLOBAL METHODS ---------------------- \\

    public static boolean deductGold(CurrencyPouch p, int a)   {
        if(p.hasEnoughGold(a))  {
            p.subtractGold(a);
            return true;
        }
        return false;
    }
    public static boolean deductPlatinum(CurrencyPouch p, int a)   {
        if(p.hasEnoughPlatinum(a))  {
            p.subtractPlatinum(a);
            return true;
        }
        return false;
    }
}
