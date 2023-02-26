package com.devops.lbnum_project.Models;

import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAODirectories {
    private final DatabaseConnection DBConnection;

    public DAODirectories() {
        this.DBConnection = new DatabaseConnection();
    }


    public List<String> getDirectories(int userId) throws SQLException {
        List<String> directories = new ArrayList<>();
        String sql = "SELECT Path FROM Directories WHERE User_Id = ?";
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                directories.add(rs.getString("Path"));
            }
        }
        return directories;
    }
    public void insertDirectory(int userId, String path) throws SQLException {
        System.out.println("userId: "+userId );
        System.out.println("userId: "+path );
        String sql = "INSERT INTO Directories (User_Id, Path) VALUES (?, ?)";
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, path);
            stmt.executeUpdate();
        }
    }
}
