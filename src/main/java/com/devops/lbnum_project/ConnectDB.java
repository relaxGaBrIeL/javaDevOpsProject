package com.devops.lbnum_project;

import java.sql.*;

public class ConnectDB {
    private Connection conn;

    public ConnectDB() {
        try {
            // Establish connection to MariaDB database
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/devopsLBN", "admin", "admin");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method for executing a SELECT query and processing the results
    public void selectData() {
        try {
            // Create a statement object
            Statement stmt = conn.createStatement();
            // Execute the SELECT query and get the result set
            ResultSet rs = stmt.executeQuery("SELECT * FROM Users");

            // Iterate over the result set and print the data to the console
            while (rs.next()) {
                int id = rs.getInt("Id_User");
                String name = rs.getString("Name");
                String email = rs.getString("Email");
                String password = rs.getString("Password");

                System.out.println(id + " " + name + " " + email + " " + password);
            }

            // Close the statement and result set objects
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    // Method for executing an INSERT, UPDATE, or DELETE query
    public void executeUpdate(String sql) {
        try {
            // Create a statement object
            Statement stmt = conn.createStatement();
            // Execute the query
            stmt.executeUpdate(sql);
            // Close the statement object
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
//
//    public void getUser(int id){
//        try{
//            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Users WHERE userID = ?");
//            ResultSet resultSet = ps.executeQuery();
//
//        }catch (SQLException e){
//           System.out.println(e.getMessage());
//        }
//
//    }
    // Method for closing the connection to the database
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
