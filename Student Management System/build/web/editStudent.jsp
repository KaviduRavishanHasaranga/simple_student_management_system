<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.nsbm.model.Student" %>
<%
    Student student = (Student) request.getAttribute("student");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Student</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <h2>Edit Student</h2>
        <form action="StudentServlet" method="post">
            <input type="hidden" name="id" value="<%= student.getId()%>">
            Name: <input type="text" name="name" value="<%= student.getName()%>" required><br/>
            Email: <input type="email" name="email" value="<%= student.getEmail()%>" required><br/>
            Course: <input type="text" name="course" value="<%= student.getCourse()%>" required><br/>
            <input type="submit" value="Update">
        </form>
        <br>
        <a href="StudentServlet">Back to Student List</a>
    </body>
</html>
