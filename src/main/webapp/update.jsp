<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.sms.model.Student" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Student - Student Management System</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="navbar.jsp" />

    <div class="container mt-4">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card shadow">
                    <div class="card-header bg-warning text-dark">
                        <h4 class="mb-0">Update Student Details</h4>
                    </div>
                    <div class="card-body">
                        <% 
                            Student student = (Student) request.getAttribute("student");
                            if (student == null) {
                                response.sendRedirect("viewStudents");
                                return;
                            }
                        %>
                        <form action="updateStudent" method="post">
                            <input type="hidden" name="id" value="<%= student.getId() %>">
                            
                            <div class="mb-3">
                                <label for="name" class="form-label">Full Name</label>
                                <input type="text" class="form-control" id="name" name="name" value="<%= student.getName() %>" required pattern="[A-Za-z\s]+" title="Only letters and spaces are allowed">
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">Email address</label>
                                <input type="email" class="form-control" id="email" name="email" value="<%= student.getEmail() %>" required>
                            </div>
                            <div class="mb-3">
                                <label for="course" class="form-label">Course</label>
                                <select class="form-select" id="course" name="course" required>
                                    <option value="Computer Science" <%= "Computer Science".equals(student.getCourse()) ? "selected" : "" %>>Computer Science</option>
                                    <option value="Information Technology" <%= "Information Technology".equals(student.getCourse()) ? "selected" : "" %>>Information Technology</option>
                                    <option value="Software Engineering" <%= "Software Engineering".equals(student.getCourse()) ? "selected" : "" %>>Software Engineering</option>
                                    <option value="Data Science" <%= "Data Science".equals(student.getCourse()) ? "selected" : "" %>>Data Science</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-warning">Update Student</button>
                            <a href="viewStudents" class="btn btn-secondary">Cancel</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
