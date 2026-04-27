<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Students - Student Management System</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="navbar.jsp" />

    <div class="container mt-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Student List</h2>
            
            <!-- Search Form -->
            <form action="searchStudent" method="get" class="d-flex">
                <input class="form-control me-2" type="search" placeholder="Search by Name" aria-label="Search" name="keyword">
                <button class="btn btn-outline-primary" type="submit">Search</button>
            </form>
        </div>
        
        <div class="table-responsive shadow-sm">
            <table class="table table-striped table-hover table-bordered mb-0">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Course</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Using JSTL to iterate over listStudents -->
                    <c:forEach var="student" items="${listStudents}">
                        <tr>
                            <td><c:out value="${student.id}" /></td>
                            <td><c:out value="${student.name}" /></td>
                            <td><c:out value="${student.email}" /></td>
                            <td><c:out value="${student.course}" /></td>
                            <td>
                                <a href="updateStudent?id=<c:out value='${student.id}' />" class="btn btn-sm btn-warning">Edit</a>
                                <a href="deleteStudent?id=<c:out value='${student.id}' />" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure you want to delete this student?');">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    
                    <c:if test="${empty listStudents}">
                        <tr>
                            <td colspan="5" class="text-center">No students found.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
        <div class="mt-3">
            <a href="add.jsp" class="btn btn-success">Add New Student</a>
        </div>
    </div>
    
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
