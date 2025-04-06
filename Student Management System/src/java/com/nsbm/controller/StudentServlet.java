package com.nsbm.controller;

import com.nsbm.dao.StudentDAO;
import com.nsbm.model.Student;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {

    private StudentDAO studentDAO;

    public void init() {
        studentDAO = new StudentDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id"); // Get ID from form
        int id = 0; // Default ID for new students

        if (idStr != null && !idStr.isEmpty()) { // Check if ID is provided
            id = Integer.parseInt(idStr);
        }

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String course = request.getParameter("course");

        Student student = new Student(id, name, email, course);

        if (id == 0) { // Insert new student if ID is 0
            studentDAO.insertStudent(student);
        } else { // Otherwise, update existing student
            studentDAO.updateStudent(student);
        }

        response.sendRedirect("StudentServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "edit":
                int id = Integer.parseInt(request.getParameter("id"));
                Student existingStudent = studentDAO.getStudentById(id);
                request.setAttribute("student", existingStudent);
                request.getRequestDispatcher("editStudent.jsp").forward(request, response);
                break;
            case "delete":
                int deleteId = Integer.parseInt(request.getParameter("id"));
                studentDAO.deleteStudent(deleteId);
                response.sendRedirect("StudentServlet");
                break;
            default:
                List<Student> students = studentDAO.listStudents();
                request.setAttribute("studentList", students);
                request.getRequestDispatcher("studentList.jsp").forward(request, response);
                break;
        }
    }
}
