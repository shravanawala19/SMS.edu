package com.sms.model;

/**
 * Model class representing a Student
 * Demonstrates Encapsulation (private fields, public getters/setters)
 */
public class Student {
    private int id;
    private String name;
    private String email;
    private String course;

    public Student() {
    }

    // Constructor without ID (used when creating a new student before DB insert)
    public Student(String name, String email, String course) {
        this.name = name;
        this.email = email;
        this.course = course;
    }

    // Constructor with ID (used when fetching from DB)
    public Student(int id, String name, String email, String course) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
