<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Student Registration</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div class="container">
            <h2>Register a Student</h2>
            <form action="StudentServlet" method="post">
                Name: <input type="text" name="name" required><br/>
                Email: <input type="email" name="email" required><br/>
                Course: <input type="text" name="course" required><br/>
                <input type="submit" value="Register">
            </form>
            <br>
            <a href="StudentServlet">View Registered Students</a>
        </div>
    </body>
</html>