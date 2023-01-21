package com.devops.lbnum_project.Models;

import com.devops.lbnum_project.Controllers.SignupResponse;
import com.devops.lbnum_project.Controllers.User;
import com.devops.lbnum_project.Controllers.LoginResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;


public class Model {
    private Connection conn;

    public Model() {
        try {
            // Establish connection to MariaDB database
            Class.forName("org.mariadb.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/devopsLBN", "root", "admin");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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
                String name = rs.getString("Firstname");
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

    /**
     * Check User existence
     *
     * @param email
     * @return true or false
     */
    public boolean checkEmail(String email) {
        boolean checkUser = false;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `Users` WHERE `Email`=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                checkUser = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return checkUser;
    }


    /**
     * Register
     * @param email
     * @param password
     * @param firstname
     * @param lastname
     * @return
     */
    public SignupResponse signup(String email, String password, String firstname, String lastname) {
        if (checkEmail(email)) {
            return new SignupResponse(false, null, "This email is already registered.");
        }
        // Hash the password using a strong hashing algorithm like bcrypt or scrypt

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO `Users` (`Firstname`, `Lastname`, `Email`, `Password`) VALUES (?, ?, ?, ?)");
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, email);
            ps.setString(4, hashedPassword);
            ps.executeUpdate();
            User user = new User(firstname, lastname, email);
            return new SignupResponse(true, user, "Successfully registered.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Login
     *
     * @param email mail
     * @param password password
     * @return LoginResponse
     */

    public LoginResponse login(String email, String password) {
        if (checkEmail(email)) {
            try {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM `Users` WHERE `Email`=?");
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String hashedPassword = rs.getString("Password");
                    if (BCrypt.checkpw(password, hashedPassword)) {

                        User user = new User(rs.getString("Firstname"), rs.getString("Lastname"), rs.getString("Email"));
                        return new LoginResponse(true, user);
                    } else {
                        return new LoginResponse(false, null);
                    }
                } else {
                    return new LoginResponse(false, null);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            return new LoginResponse(false, null);
        }
    }
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
