package com.sms.dao;

import com.sms.model.User;
import com.sms.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    /**
     * Validates user credentials against the database
     * @param username The username input
     * @param password The password input
     * @return true if valid, false otherwise
     */
    public boolean validateUser(String username, String password) {
        boolean isValid = false;
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                isValid = true;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return isValid;
    }
}
