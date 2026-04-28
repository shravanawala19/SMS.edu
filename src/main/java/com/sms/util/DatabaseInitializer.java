package com.sms.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.Statement;

/**
 * Runs on application startup to ensure the database schema exists.
 * Safe to re-run — all statements use IF NOT EXISTS.
 */
@WebListener
public class DatabaseInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("[DatabaseInitializer] Running schema initialization...");
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Create users table
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS users (" +
                "  id INT PRIMARY KEY AUTO_INCREMENT," +
                "  username VARCHAR(50) NOT NULL UNIQUE," +
                "  password VARCHAR(255) NOT NULL" +
                ")"
            );

            // Create students table
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS students (" +
                "  id INT PRIMARY KEY AUTO_INCREMENT," +
                "  name VARCHAR(100) NOT NULL," +
                "  email VARCHAR(100) NOT NULL," +
                "  course VARCHAR(100) NOT NULL" +
                ")"
            );

            // Insert default admin user only if not exists
            stmt.executeUpdate(
                "INSERT IGNORE INTO users (username, password) VALUES ('admin', 'admin123')"
            );

            // Insert sample students only if table is empty
            stmt.executeUpdate(
                "INSERT IGNORE INTO students (id, name, email, course) VALUES " +
                "(1, 'John Doe', 'john.doe@example.com', 'Computer Science')," +
                "(2, 'Jane Smith', 'jane.smith@example.com', 'Information Technology')," +
                "(3, 'Alice Johnson', 'alice.j@example.com', 'Software Engineering')"
            );

            System.out.println("[DatabaseInitializer] Schema initialized successfully.");

        } catch (Exception e) {
            System.err.println("[DatabaseInitializer] Schema init failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // nothing to clean up
    }
}
