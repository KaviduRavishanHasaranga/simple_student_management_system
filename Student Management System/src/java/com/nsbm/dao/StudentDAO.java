package com.nsbm.dao;

import com.nsbm.model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/studentdb";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_SQL = "INSERT INTO students (name, email, course) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_SQL = "SELECT * FROM students";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM students WHERE id=?";
    private static final String UPDATE_SQL = "UPDATE students SET name=?, email=?, course=? WHERE id=?";
    private static final String DELETE_SQL = "DELETE FROM students WHERE id=?";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertStudent(Student student) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_SQL)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getCourse());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> listStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                students.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("course")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public Student getStudentById(int id) {
        Student student = null;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                student = new Student(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("course"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public void updateStudent(Student student) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getCourse());
            ps.setInt(4, student.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
