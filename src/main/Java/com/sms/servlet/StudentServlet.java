package com.sms.servlet;

import com.sms.model.Student;
import com.sms.service.StudentService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {

    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            // Lazy or safe initialization of services during servlet startup
            this.studentService = new StudentService();
        } catch (Exception e) {
            // Log error without letting init() fail silently
            throw new ServletException("Failed to initialize StudentServlet due to DB connectivity issue", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            List<Student> students = studentService.getAllStudents();
            out.println("<h2>Student List</h2>");
            out.println("<ul>");
            for (Student student : students) {
                out.println("<li>" + student + "</li>");
            }
            out.println("</ul>");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("<h3>Unable to fetch students. Please verify database availability.</h3>");
        }
    }
}