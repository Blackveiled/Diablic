package com.Blackveiled.Diablic;

import com.Blackveiled.Diablic.Commands.Handlers.WarpHandler;
import com.Blackveiled.Diablic.Database.DatabaseManager;
import com.Blackveiled.Diablic.Entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

import javax.sql.rowset.CachedRowSet;
import java.io.*;
import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WarpManager {

    private Diablic instance;

    private Map<String, WarpHandler> warps = new HashMap();

    public WarpManager(Diablic instance)    {
        this.instance = instance;
    }

    public void load() {
        String path = "plugins/Diablic/Warps/";
        String fileName = path + "warplist.dat";
        File file = new File(fileName);

        if (file.exists()) {
            try {
                FileReader reader = new FileReader(file);
                JSONTokener tokener = new JSONTokener(reader);
                JSONObject jsonobject = new JSONObject(tokener);

                JSONArray ar = jsonobject.getJSONArray("warps");
                for(int i = 0; i < ar.length(); i++)    {
                    JSONObject obj = (JSONObject) ar.get(i);
                    addWarp(obj.getString("name"), obj.getString("world"), obj.getDouble("x"), obj.getDouble("y"), obj.getDouble("z"));
                }

            } catch (FileNotFoundException Exception) {

            }
        }
    }


    public void save()  {
        // Save All Warps Contained Within WarpManager
        try {


            String path = "plugins/Diablic/Warps/";
            String fileName = path + "warplist.dat";

            File file  = new File(fileName);

            file.createNewFile();


            String FileOut = "";

            JSONStringer s = new JSONStringer();
            s.object()
                    .key("warps")
                    .array();
            for(WarpHandler w : warps.values())  {
                s.object()
                        .key("name")
                        .value(w.getName())
                        .key("world")
                        .value(w.getWorld().getName())
                        .key("x")
                        .value(w.getX())
                        .key("y")
                        .value(w.getY())
                        .key("z")
                        .value(w.getZ())
                        .endObject();
            }

            s.endArray()
                    .endObject();

            FileWriter writer = new FileWriter(file);
            writer.write(s.toString());
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean addWarp(final String n,final String w,final double x,final double y,final double z)   {
        if(hasWarp(n)) return false;
            BukkitRunnable r = new BukkitRunnable() {
                @Override
                public void run()   {
                    final WarpHandler wa = new WarpHandler(n, w, x, y, z);
                    DatabaseManager dbm = instance.getDatabaseManager();
                    try {
                        Connection connection = dbm.getDataSource().getConnection();
                        PreparedStatement prpstm = connection.prepareStatement("REPLACE INTO `warps` SET `name`=?, `world`=?, `x`=" + wa.getX() + ", `y`=" + wa.getY() + ", `z`=" + wa.getZ() + ";");
                        prpstm.setString(1, n);
                        prpstm.setString(2, w);
                        prpstm.execute();

                        prpstm.close();
                        connection.close();

                        warps.put(n, wa);
                    }   catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            };
            r.runTaskAsynchronously(instance);

        //
        /*

            BukkitRunnable r = new BukkitRunnable() {
                @Override
                public void run() {
                    String dbSaveString = "REPLACE INTO `warps` SET `name`=?, `world`=?, `x`=" + wa.getX() + ", `y`=" + wa.getY() + ", `z`=" + wa.getZ() + ";";
                    final PreparedStatement statement = instance.getDatabaseManager().prepareStatement(dbSaveString, wa.getName(), wa.getWorld().getName());
                    instance.getDatabaseManager().execute(statement);
                }
            };
            r.runTaskAsynchronously(instance);
            */


        return true;
    }

    public void removeWarp(final String n)    {
        if(hasWarp(n)) {
            BukkitRunnable r = new BukkitRunnable() {
                @Override
                public void run()   {
                    DatabaseManager dbm = instance.getDatabaseManager();
                    try {
                        Connection connection = dbm.getDataSource().getConnection();
                        PreparedStatement prpstm = connection.prepareStatement("DELETE FROM `warps` WHERE `name`=?;");
                        prpstm.setString(1, n);
                        prpstm.execute();

                        prpstm.close();
                        connection.close();

                        warps.remove(n);
                    }   catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            };
            r.runTaskAsynchronously(instance);
        }
    }

    public boolean hasWarp(String n)   {
        if(warps.containsKey(n)) return true;
        return false;
    }

    public WarpHandler getWarp(String n)    {
        if(hasWarp(n)) return warps.get(n);
        return null;
    }

    public Collection<WarpHandler> values()    {
        return this.warps.values();
    }

/*    public void saveDatabase()  {
        try {
            instance.getDatabaseManager().openConnection();
            Connection connection = instance.getDatabaseManager().getConnection();
            for(WarpHandler w : warps.values()) {
                PreparedStatement statement = connection.prepareStatement("REPLACE INTO `warps` SET `name`= ?, `world`= ?, `x`= "+w.getX()+"," +
                        "`y`= "+w.getY()+"," +
                        "`z` = "+w.getZ()+";");
                statement.setString(1, w.getName());
                statement.setString(2, w.getWorld().getName());
                statement.execute();
            }
        }   catch (SQLException ex) {

        }   catch (ClassNotFoundException ex)   {

        }
        finally {
            try {
                instance.getDatabaseManager().getConnection().close();
            }   catch (SQLException ex) {

            }
        }
    } */

    /* public void loadDatabase()  {
        try {
            instance.getDatabaseManager().openConnection();
            Connection connection = instance.getDatabaseManager().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `warps`");
            ResultSet results = statement.executeQuery("SELECT * FROM `warps`");
            while(results.next())   {
                WarpHandler newWarp = new WarpHandler(results.getString("name"), results.getString("world"), results.getDouble("x"),
                        results.getDouble("y"), results.getDouble("z"));
                warps.put(results.getString("name"), newWarp);
            }
        }   catch (SQLException ex) {

        }   catch (ClassNotFoundException ex)   {

        }
        finally {
            try {
                instance.getDatabaseManager().getConnection().close();
            }   catch (SQLException ex) {

            }
        }
    } */

    public void loadDatabase() throws SQLException {
        String dbLoadString = "SELECT * FROM `warps`;";
        final PreparedStatement statement = instance.getDatabaseManager().prepareStatement(dbLoadString);
        CachedRowSet res = instance.getDatabaseManager().query(statement);
        while(res.next())   {
            WarpHandler newWarp = new WarpHandler(res.getString("name"), res.getString("world"), res.getDouble("x"), res.getDouble("y"), res.getDouble("z"));
            warps.put(res.getString("name"), newWarp);
            Bukkit.broadcastMessage(res.getString("name") + " added to Warps List!");
        }
    }

}

