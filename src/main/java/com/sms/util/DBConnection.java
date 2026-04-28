package com.sms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Read from environment variables; fall back to local dev defaults
    private static final String URL = System.getenv("DB_URL") != null
            ? System.getenv("DB_URL")
            : "jdbc:mysql://localhost:3306/sms?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private static final String USER = System.getenv("DB_USER") != null
            ? System.getenv("DB_USER")
            : "root";

    private static final String PASSWORD = System.getenv("DB_PASSWORD") != null
            ? System.getenv("DB_PASSWORD")
            : "";

    /**
     * Establishes and returns a database connection.
     * Credentials are pulled from environment variables (DB_URL, DB_USER, DB_PASSWORD).
     * Falls back to localhost defaults for local development.
     *
     * @return Connection object, or null if connection fails
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println("[DBConnection] Connection failed: " + e.getMessage());
        }
        return connection;
    }
}
