package com.codecool.stackoverflowtw.database;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

    private final String dbName;
    private final String userName;
    private final String password;
    private final String url;

    public Connection(String dbName, String userName, String password, String url) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
        this.url = url;
    }

    public java.sql.Connection getConnection() {
        try {
            return DriverManager.getConnection(url, userName, password);
        } catch (SQLException ex) {
            System.err.println("Could not create database connection.");
            throw new RuntimeException(ex);
        }
    }
}
