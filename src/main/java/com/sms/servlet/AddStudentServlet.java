package com.sms.servlet;

import com.sms.dao.StudentDAO;
import com.sms.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {
    private StudentDAO studentDAO;

    public void init() {
        studentDAO = new StudentDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        // Check if user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String course = request.getParameter("course");
        
        Student newStudent = new Student(name, email, course);
        
        if (studentDAO.addStudent(newStudent)) {
            response.sendRedirect("viewStudents");
        } else {
            request.setAttribute("errorMessage", "Error adding student");
            request.getRequestDispatcher("add.jsp").forward(request, response);
        }
    }
}
