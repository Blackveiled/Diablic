package com.Blackveiled.Diablic.Database;

import com.Blackveiled.Diablic.Diablic;

public class DatabaseManager extends Database{

    private Diablic instance;

    private String host;
    private int port;
    private String user;
    private String password;
    private String schema;

    public DatabaseManager(Diablic instance, String host, int port, String user, String password, String schema)    {
        super("com.mysql.jdbc.Driver", "jdbc:mysql://" + host + ":" + port + "/" + schema +"?useSSL=false", user, password, instance);

        this.instance = instance;
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.schema = schema;

    }


}
