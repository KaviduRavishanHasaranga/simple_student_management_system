<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.nsbm.model.Student" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Registered Students</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div class="container">
            <h2>List of Registered Students</h2>
            <table border="1">
                <tr><th>ID</th><th>Name</th><th>Email</th><th>Course</th><th>Actions</th></tr>
                        <%
                            List<Student> students = (List<Student>) request.getAttribute("studentList");
                            for (Student s : students) {
                        %>
                <tr>
                    <td><%= s.getId()%></td>
                    <td><%= s.getName()%></td>
                    <td><%= s.getEmail()%></td>
                    <td><%= s.getCourse()%></td>
                    <td>
                        <a href="StudentServlet?action=edit&id=<%= s.getId()%>">Edit</a>
                        <a href="StudentServlet?action=delete&id=<%= s.getId()%>" onclick="return confirm('Delete?');">Delete</a>
                    </td>
                </tr>
                <% }%>
            </table>
            <a href="studentForm.jsp">Register Student</a>
        </div>
    </body>
</html>
