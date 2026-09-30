package com.sms.service;

import com.sms.dao.StudentDAO;
import com.sms.exception.StudentNotFoundException;
import com.sms.model.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private final StudentDAO dao = new StudentDAO();
    private final List<Student> students = new ArrayList<>();

    public StudentService() throws SQLException {
        students.addAll(dao.findAll());   // load DB rows into the ArrayList
    }


    public synchronized void addStudent(int id, String name, int age, String course, double marks)
            throws SQLException {
        if (id <= 0) throw new IllegalArgumentException("Student ID must be a positive number.");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name is required.");
        if (course == null || course.isBlank()) throw new IllegalArgumentException("Course is required.");
        if (age <= 17) throw new IllegalArgumentException("Age must be greater than 17.");
        validateMarks(marks);

        for (Student s : students) {
            if (s.getStudentId() == id) {
                throw new IllegalArgumentException("Duplicate ID: student " + id + " already exists.");
            }
        }
        Student s = new Student(id, name.trim(), age, course.trim(), marks);
        dao.insert(s);
        students.add(s);
    }
    public synchronized List<Student> getAll() {
        return new ArrayList<>(students);
    }

    public synchronized Student search(int id) throws StudentNotFoundException {
        for (Student s : students) {
            if (s.getStudentId() == id) return s;
        }
        throw new StudentNotFoundException("Student with ID " + id + " was not found.");
    }

    public synchronized void updateMarks(int id, double marks)
            throws StudentNotFoundException, SQLException {
        validateMarks(marks);
        Student s = search(id);
        dao.updateMarks(id, marks);
        s.setMarks(marks);
    }

    public synchronized void delete(int id) throws StudentNotFoundException, SQLException {
        Student s = search(id);
        dao.delete(id);
        students.remove(s);
    }

    private void validateMarks(double marks) {
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100.");
        }
    }

    public List<Student> getAllStudents() throws SQLException {

        try {
            return StudentDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
