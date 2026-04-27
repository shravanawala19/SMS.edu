package com.sms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/sms";
    private static final String USER = "root"; // Assuming default XAMPP/MySQL username
    private static final String PASSWORD = ""; // Assuming default XAMPP/MySQL password (empty)

    /**
     * Establishes and returns a database connection.
     * @return Connection object
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Get connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Database Connection Failed: " + e.getMessage());
        }
        return connection;
    }
}
