package com.Blackveiled.Diablic.Entity;

import com.Blackveiled.Diablic.Chat.ChatChannel;
import com.Blackveiled.Diablic.Diablic;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {

    private Diablic instance;

    private Map<UUID, Player> players = new HashMap();

    public PlayerManager(Diablic instance)  {
        this.instance = instance;
    }

    public Map<UUID, Player> getPlayers()   {
        return this.players;
    }

    public Player getPlayer(UUID uuid)  {
        if(players.containsKey(uuid))   {
            return players.get(uuid);
        }
        return null;
    }

    public boolean hasPlayer(UUID uuid) {
        if(players.containsKey(uuid)) return true;
        return false;
    }

    public void updatePlayer(Player player) {
        players.put(player.getUniqueId(), player);
    }

    public boolean containsPlayer(UUID uuid)    {
        if(players.containsKey(uuid)) return true;
        return false;
    }

    public void removePlayer(UUID uuid) {
        if(players.containsKey(uuid)) players.remove(uuid);
    }

    public void savePlayerToDatabase(final UUID uuid) {
        if(containsPlayer(uuid))    {
            final Player pd = players.get(uuid);
            BukkitRunnable r = new BukkitRunnable() {
                @Override
                public void run()   {
                    try {
                        Connection connection = instance.getDatabaseManager().getDataSource().getConnection();
                        PreparedStatement prpstm = connection.prepareStatement("REPLACE INTO `players` SET `uuid`='"+uuid.toString()+"'," +
                                "`name`='"+pd.getName()+"',"+
                                "`prefix`=?, `suffix`=?, `buildmode`="+pd.buildMode+", `banned`="+pd.banned+", `banreason`=?,"
                        + "`lastip`='"+pd.lastip+"';");
                        prpstm.setString(1, pd.getPrefix());
                        prpstm.setString(2, pd.getSuffix());
                        prpstm.setString(3, pd.banReason);

                        prpstm.execute();

                        prpstm.close();
                        connection.close();

                    }   catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            };
            r.runTaskAsynchronously(instance);
        }

    }

    public void loadPlayerFromDatabase(final UUID uuid) {
        if(!containsPlayer(uuid))    {
            BukkitRunnable r = new BukkitRunnable() {
                @Override
                public void run()   {
                    try {
                        Player pd = new Player(uuid);
                        Connection connection = instance.getDatabaseManager().getDataSource().getConnection();

                        int count2 = 0;

                        PreparedStatement count = connection.prepareStatement("SELECT COUNT(*) FROM `players` WHERE `uuid`='" + uuid.toString() + "';");
                        ResultSet res2 = count.executeQuery();
                        while (res2.next()) {
                            count2 = res2.getInt("COUNT(*)");
                        }
                        res2.close();
                        count.close();

                        if (count2 > 0) {
                            PreparedStatement prpstm = connection.prepareStatement("SELECT * FROM `players` WHERE `uuid`='" + uuid.toString() + "';");
                            ResultSet res = prpstm.executeQuery();
                            while (res.next()) {
                                pd.setPrefix(res.getString("prefix"));
                                pd.setSuffix(res.getString("suffix"));
                                pd.buildMode = res.getBoolean("buildmode");
                                pd.banned = res.getBoolean("banned");
                                pd.banReason = res.getString("banreason");
                            }

                            res.close();
                            prpstm.close();



                        }

                        connection.close();

                        instance.getPlayerManager().updatePlayer(pd);
                        ChatChannel Global = instance.getChannelManager().getChatChannel(Diablic.global);
                        Global.addPlayerToChannel(uuid);
                        instance.getChannelManager().updateChatChannel(Diablic.global, Global);
                        instance.getPlayerManager().savePlayerToDatabase(uuid);
                    }
                    catch(SQLException ex)  {
                        ex.printStackTrace();
                    }
                }
            };
            r.runTaskAsynchronously(instance);
        }
    }
}
