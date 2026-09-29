package com.sms.servlet;

import com.sms.exception.StudentNotFoundException;
import com.sms.model.Student;
import com.sms.service.StudentService;
import com.sms.util.InputSanitizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StudentServlet extends HttpServlet {

    private StudentService service;

    @Override
    public void init() throws ServletException {
        try {
            service = new StudentService();
        } catch (SQLException e) {
            throw new ServletException("Could not connect to database", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("search".equals(action)) {
                int id = Integer.parseInt(req.getParameter("studentId").trim());
                Student s = service.search(id);
                req.setAttribute("students", List.of(s));
                req.setAttribute("message", "Student found.");
            } else if ("exit".equals(action)) {
                req.getSession().invalidate();
                resp.setContentType("text/html;charset=UTF-8");
                resp.getWriter().println("<h3>Goodbye! Session closed.</h3>");
                return;
            } else {
                req.setAttribute("students", service.getAll());   // View All
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Please enter a valid numeric ID.");
            req.setAttribute("students", service.getAll());
        } catch (StudentNotFoundException e) {
            req.setAttribute("error", e.getMessage());
            req.setAttribute("students", service.getAll());
        }
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        try {
            switch (action == null ? "" : action) {
                case "add": {
                    int id = Integer.parseInt(req.getParameter("studentId").trim());
                    String name = req.getParameter("name");
                    if (!InputSanitizer.isValidName(name)) {
                        throw new IllegalArgumentException("Name may contain only letters, spaces, dots and hyphens.");
                    }
                    int age = Integer.parseInt(req.getParameter("age").trim());
                    String course = req.getParameter("course");
                    double marks = Double.parseDouble(req.getParameter("marks").trim());
                    service.addStudent(id, name, age, course, marks);
                    req.setAttribute("message", "Student added successfully.");
                    break;
                }
                case "updateMarks": {
                    int id = Integer.parseInt(req.getParameter("studentId").trim());
                    double marks = Double.parseDouble(req.getParameter("marks").trim());
                    service.updateMarks(id, marks);
                    req.setAttribute("message", "Marks updated successfully.");
                    break;
                }
                case "delete": {
                    int id = Integer.parseInt(req.getParameter("studentId").trim());
                    service.delete(id);
                    req.setAttribute("message", "Student deleted successfully.");
                    break;
                }
                default:
                    req.setAttribute("error", "Unknown action.");
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid input: please enter numbers where numbers are expected.");
        } catch (IllegalArgumentException | StudentNotFoundException e) {
            req.setAttribute("error", e.getMessage());
        } catch (SQLException e) {
            req.setAttribute("error", "Database error. Please try again.");
            log("Database error", e);
        }
        req.setAttribute("students", service.getAll());
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

}
