package com.sms.dao;

import com.sms.model.Student;
import com.sms.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // Add a new student
    public boolean addStudent(Student student) {
        boolean rowInserted = false;
        String sql = "INSERT INTO students (name, email, course) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getCourse());
            
            rowInserted = stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowInserted;
    }

    // Get all students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String course = rs.getString("course");
                
                students.add(new Student(id, name, email, course));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Get student by ID
    public Student getStudentById(int id) {
        Student student = null;
        String sql = "SELECT * FROM students WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String course = rs.getString("course");
                
                student = new Student(id, name, email, course);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    // Update student
    public boolean updateStudent(Student student) {
        boolean rowUpdated = false;
        String sql = "UPDATE students SET name = ?, email = ?, course = ? WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getCourse());
            stmt.setInt(4, student.getId());
            
            rowUpdated = stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    // Delete student
    public boolean deleteStudent(int id) {
        boolean rowDeleted = false;
        String sql = "DELETE FROM students WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            rowDeleted = stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    // Search students by name
    public List<Student> searchStudentsByName(String keyword) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE name LIKE ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String course = rs.getString("course");
                
                students.add(new Student(id, name, email, course));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
