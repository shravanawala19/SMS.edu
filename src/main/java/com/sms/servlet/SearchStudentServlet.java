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
import java.util.List;

@WebServlet("/searchStudent")
public class SearchStudentServlet extends HttpServlet {
    private StudentDAO studentDAO;

    public void init() {
        studentDAO = new StudentDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        // Check if user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
            
        String keyword = request.getParameter("keyword");
        
        List<Student> listStudents;
        if (keyword != null && !keyword.trim().isEmpty()) {
            listStudents = studentDAO.searchStudentsByName(keyword);
        } else {
            listStudents = studentDAO.getAllStudents(); // If empty search, show all
        }
        
        request.setAttribute("listStudents", listStudents);
        request.getRequestDispatcher("view.jsp").forward(request, response);
    }
}
