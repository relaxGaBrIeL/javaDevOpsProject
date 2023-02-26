package com.devops.lbnum_project.Models;
import java.sql.*;


public class DAOTask {
    private final DatabaseConnection DBConnection;

    public DAOTask() {
        this.DBConnection = new DatabaseConnection();
    }

    // Method to retrieve all todos from the database
    public ResultSet getAllTodos() throws SQLException {
        Statement stmt = DBConnection.getConnection().createStatement();
        String sql = "SELECT * FROM todo";
        return stmt.executeQuery(sql);
    }

    public void addTodo(String title, String description, Date dueDate, boolean status) throws SQLException {
        String sql = "INSERT INTO todo (title, description, due_date, status) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
        pstmt.setString(1, title);
        pstmt.setString(2, description);
        pstmt.setDate(3, dueDate);
        pstmt.setBoolean(4, status);
        pstmt.executeUpdate();
    }
    // Method to update an existing todo in the database
    public void updateTodo(int id, String title, String description, Date dueDate, boolean status) throws SQLException {
        String sql = "UPDATE todo SET title = ?, description = ?, due_date = ?, status = ? WHERE id = ?";
        PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
        pstmt.setString(1, title);
        pstmt.setString(2, description);
        pstmt.setDate(3, dueDate);
        pstmt.setBoolean(4, status);
        pstmt.setInt(5, id);
        pstmt.executeUpdate();
    }
    // Method to delete an existing todo from the database
    public void deleteTodo(int id) throws SQLException {
        String sql = "DELETE FROM todo WHERE id = ?";
        PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }

}
