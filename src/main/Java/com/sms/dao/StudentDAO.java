package com.sms.dao;

import com.sms.model.Student;
import com.sms.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public static List<Student> findAll() throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT id, name, age, course, marks FROM students ORDER BY id";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Student(rs.getInt(1), rs.getString(2),
                        rs.getInt(3), rs.getString(4), rs.getDouble(5)));
            }
        }
        return list;
    }

    public void insert(Student s) throws SQLException {
        String sql = "INSERT INTO students (id, name, age, course, marks) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, s.getStudentId());
            ps.setString(2, s.getName());
            ps.setInt(3, s.getAge());
            ps.setString(4, s.getCourse());
            ps.setDouble(5, s.getMarks());
            ps.executeUpdate();
        }
    }

    public void updateMarks(int id, double marks) throws SQLException {
        String sql = "UPDATE students SET marks = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, marks);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
