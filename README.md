# Student Management System

A complete full-stack Java web application using OOP concepts, Servlets/JSP, JDBC, and MySQL.

## Tech Stack
- **Backend:** Java, Servlets, JSP, JDBC
- **Database:** MySQL
- **Frontend:** HTML, CSS, Bootstrap 5, JSTL
- **Server:** Apache Tomcat
- **Build Tool:** Maven

## Setup Instructions

### 1. Database Setup
1. Open your MySQL client (e.g., MySQL Workbench, phpMyAdmin, or CLI).
2. Run the SQL script provided in the `database.sql` file in the root directory. This will create the `sms` database, `users` and `students` tables, and insert default data.
3. **Database Credentials:**
   The connection is configured in `src/main/java/com/sms/util/DBConnection.java`.
   By default, it uses:
   - URL: `jdbc:mysql://localhost:3306/sms`
   - Username: `root`
   - Password: `""` (Empty password)
   *If your MySQL setup uses a different username/password, update them in `DBConnection.java` before running.*

### 2. Running the Application in Eclipse / IntelliJ
Since this is a standard Maven project:
1. Open your IDE (Eclipse or IntelliJ IDEA).
2. Select **Import** -> **Existing Maven Project** and select the root directory (`DBMS Site`).
3. Allow the IDE to download the dependencies from `pom.xml`.
4. Add your **Apache Tomcat Server** to the IDE.
5. Right-click the project -> **Run As** -> **Run on Server**.
6. Select your Tomcat server and finish.

### 3. Application URL
The application will be accessible at:
`http://localhost:8080/StudentManagementSystem-1.0-SNAPSHOT/` (or depending on your server config, usually `http://localhost:8080/`).
It will redirect to `login.jsp`.

### 4. Default Login Credentials
- **Username:** admin
- **Password:** admin123

## Features
- **Authentication:** Secure login/logout using `HttpSession`.
- **CRUD Operations:** Create, Read, Update, and Delete students.
- **Search:** Find students by name using the search bar (uses SQL `LIKE`).
- **Responsive UI:** Clean interface built with Bootstrap.
- **Form Validation:** Basic HTML5 validation for names, emails, etc.
