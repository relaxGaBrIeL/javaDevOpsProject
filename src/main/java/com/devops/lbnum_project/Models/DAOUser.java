package com.devops.lbnum_project.Models;

import com.devops.lbnum_project.Controllers.User;
import com.devops.lbnum_project.Controllers.authentication.LoginResponse;
import com.devops.lbnum_project.Controllers.authentication.SignupResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOUser {
    private final DatabaseConnection DBConnection;

    public DAOUser() {
        this.DBConnection = new DatabaseConnection();
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement stmt = DBConnection.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Users");
            while (rs.next()) {
                User user = new User("","","");

                user.setlName(rs.getString("Lastname"));
                user.setfName(rs.getString("Firstname"));
                user.setEmail(rs.getString("Email"));

                users.add(user);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close();
        }
        return users;
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
            PreparedStatement ps = DBConnection.getConnection().prepareStatement("SELECT * FROM `Users` WHERE `Email`=?");
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
            PreparedStatement ps = DBConnection.getConnection().prepareStatement("INSERT INTO `Users` (`Firstname`, `Lastname`, `Email`, `Password`) VALUES (?, ?, ?, ?)");
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, email);
            ps.setString(4, hashedPassword);
            ps.executeUpdate();
            User user = new User(firstname, lastname, email);
            return new SignupResponse(true, user, "Successfully registered.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBConnection.close();
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
                PreparedStatement ps = DBConnection.getConnection().prepareStatement("SELECT * FROM `Users` WHERE `Email`=?");
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
            }finally {
                DBConnection.close();
            }
        } else {
            return new LoginResponse(false, null);
        }
    }

}
