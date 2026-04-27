CREATE DATABASE IF NOT EXISTS sms;
USE sms;

CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    course VARCHAR(100) NOT NULL
);

-- Insert a default admin user (password: admin123)
INSERT INTO users (username, password) VALUES ('admin', 'admin123');

-- Insert some sample students
INSERT INTO students (name, email, course) VALUES 
('John Doe', 'john.doe@example.com', 'Computer Science'),
('Jane Smith', 'jane.smith@example.com', 'Information Technology'),
('Alice Johnson', 'alice.j@example.com', 'Software Engineering');
