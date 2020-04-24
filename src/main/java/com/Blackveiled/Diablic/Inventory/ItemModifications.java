package com.Blackveiled.Diablic.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemModifications {

    private boolean soulbound = false;
    private UUID soulboundOwner;
    private int currentDurability;
    private List<Socket> sockets = new ArrayList();
    private int socketCount;

    // Ancient Attributes
    private boolean ancient = false;
    private boolean ancientPrimal = false;

    public ItemModifications()  {

    }

    public ItemModifications(int SocketCount)   {
        this.sockets = new ArrayList(SocketCount);
        this.socketCount = SocketCount;
    }

    public boolean isSoulbound()   {
        return soulbound;
    }

    public UUID getSoulboundOwner() {
        return soulboundOwner;
    }

    public void setSoulboundOwner(UUID uuid) {
        this.soulboundOwner = uuid;
    }

    public int getCurrentDurability()   {
        return currentDurability;
    }

    public void setDurability(int durability)   {
        currentDurability = durability;
    }

    // Conditions

    public boolean isAncient() { return ancient; }
    public boolean isAncientPrimal() { return ancientPrimal; }

    public List<Socket> getSockets()    { return sockets; }

    /**
     * Adds sockets to the current array list.  Replaces with a brand new ArrayList object with a higher base-value of sockets.
     * @param amt
     */
    public void addSocket(int amt)  {
        List<Socket> oldList = this.sockets;
        List<Socket> newList = new ArrayList(socketCount + amt);
        for(Socket s : this.sockets)    {
            newList.add(s);
        }
        socketCount += amt;
        this.sockets = newList;
    }

    /**
     * Adds a DIAItem to a Socket for an item.  This method will return false if there is no available socket.
     * @param i DIAItem Reference
     * @return boolean
     */
    public boolean addItemToSocket(final DIAItem i)   {
        int c = 0;
        for(Socket s : this.sockets)    {
            if(s == null)   {
                Socket ns = new Socket(i);
                this.sockets.set(c, ns);
                return true; // Returns true if socket slot is available.
            }
            c++;
        }
        return false; // Returns false if no socket is available.
    }
}
