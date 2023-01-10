package test.lbnum_project;

import com.devops.lbnum_project.ConnectDB;

public class TestDB {
    public static void main(String[] args) {
        // Create a com.devops.project.ConnectDB object
        ConnectDB db = new ConnectDB();
        // Execute a SELECT query and process the results
        db.selectData();
        // Execute an INSERT query
        db.executeUpdate("INSERT INTO Users (Name, Email, Password) VALUES ('John', 'john@example.com', 'password123')");
        // Close the connection to the database
        db.closeConnection();
    }
}