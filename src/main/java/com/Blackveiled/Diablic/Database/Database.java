package com.Blackveiled.Diablic.Database;

import com.sun.rowset.CachedRowSetImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariProxyConnection;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import javax.sql.rowset.CachedRowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.*;

/*
 * ByteUtils - Developed by VirtualByte (Lewes D. B.)
 */
public abstract class Database {

    private String           jdbcURL;
    public HikariDataSource dataSource;
    private JavaPlugin       plugin;

    /*
     * Construct a database instance.
     *
     * @param className The class name used to get the driver.
     * @param jdbcURL   A JDBC url to use for connecting.
     * @param username  Username to connect with.
     * @param password  Password to authenticate username.
     * @param plugin    A plugin instance for the schedulers to be assigned to.
     */
    public Database(String className, String jdbcURL, String username, String password, JavaPlugin plugin) {
        this.jdbcURL = jdbcURL;
        this.plugin = plugin;

        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(jdbcURL);

        config.setUsername(username);
        config.setPassword(password);

        config.setLeakDetectionThreshold(10000);
        config.setMaximumPoolSize(50);


        try {
            dataSource = new HikariDataSource(config);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
     * Connects the data pool to the database.
     */
    public void connect() {
        isConnected();
    }

    /*
     * Disconnects (shutdown) the data pool and all connections.
     */
    public void disconnect() {
        dataSource.evictConnection(getConnection());
    }

    /*
     * Query the database and return a cached result.
     *
     * @param query The statement to be queried.
     * @return      Cached rowset returned from query.
     */
    public CachedRowSet query(final PreparedStatement preparedStatement) {
        CachedRowSet rowSet = null;

        if (isConnected()) {
            try {
                ExecutorService exe = Executors.newCachedThreadPool();

                Future<CachedRowSet> future = exe.submit(new Callable<CachedRowSet>() {
                    public CachedRowSet call() {
                        try {
                            ResultSet resultSet = preparedStatement.executeQuery();

                            Connection connection = preparedStatement.getConnection();

                            CachedRowSet cachedRowSet = new CachedRowSetImpl();
                            cachedRowSet.populate(resultSet);
                            resultSet.close();

                            connection.close();

                            preparedStatement.close();

                            if (cachedRowSet.next()) {
                                return cachedRowSet;
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        return null;
                    }
                });

                if (future.get() != null) {
                    rowSet = future.get();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        return rowSet;
    }

    /*
     * Execute a query
     *
     * @param preparedStatement query to be executed.
     */
    public void execute(final PreparedStatement preparedStatement) {
        if (isConnected()) {
            Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
                @Override
                public void run() {
                    try {
                        preparedStatement.execute();
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /*
     * Prepare a statement
     *
     * @param query Query to be prepared.
     * @param vars  Variables to be replaced from ?.
     * @return      a prepared statement.
     */
    public PreparedStatement prepareStatement(String query, String... vars) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);

            int x = 0;

            if (query.contains("?") && vars.length != 0) {
                for (String var : vars) {
                    x++;
                    preparedStatement.setString(x, var);
                }
            }
            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /*
     * Get a connection from the data pool
     *
     * @return a connection.
     */
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /*
     * Check if the data pool is connected.
     *
     * @return connected Whether the data pool is connected or not.
     */
    public boolean isConnected() {
        try {
            dataSource.getConnection();
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public HikariDataSource getDataSource() {
        return this.dataSource;
    }

}