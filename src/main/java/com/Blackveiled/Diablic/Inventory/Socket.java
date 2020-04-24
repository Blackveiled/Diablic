package com.Blackveiled.Diablic.Inventory;

public class Socket {

    public enum SocketType  {

        RED(0), BLUE(1), YELLOW(2), GREEN(3), PRIME(4);

        private int type;

        SocketType(int type)    {
            this.type = type;
        }
    }

    private SocketType type;
    private int item; // The item socketted into this socket.  Used as a reference, basically.

    public Socket(SocketType type) {
        this.type = type;
    }

    public Socket(DIAItem item) { this.item = item.getIndex(); }

    public DIAItem getItem()    { return DIAInventoryCache.getItem(item); }

    public SocketType getType() { return type; }
}
