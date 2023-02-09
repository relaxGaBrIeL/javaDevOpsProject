package com.devops.lbnum_project.Models;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnection {
    private Connection connection;
    private Properties config;

    public DatabaseConnection() {
        try {
            loadConfig();
            Class.forName("org.mariadb.jdbc.Driver");
            this.connection = DriverManager.getConnection(
                    config.getProperty("db.url"),
                    config.getProperty("db.username"),
                    config.getProperty("db.password")
            );
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadConfig() {
        config = new Properties();
        InputStream input;
        try {
            input = new FileInputStream("config.properties");
            config.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void close() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
