package com.boomi.leavetracking.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/createStudent")
public class CreateStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String rollNo = request.getParameter("rollNo");
        String department = request.getParameter("department");
        // create a new student record in the database
        boolean success = createStudent(name, rollNo, department);
        if (success) {
            // redirect to student records page
            response.sendRedirect(request.getContextPath() + "/studentRecords.jsp");
        } else {
            // show error message and redirect back to create student form
            request.setAttribute("error", "Failed to create student record");
            request.getRequestDispatcher("/createStudent.jsp").forward(request, response);
        }
    }

    private boolean createStudent(String name, String rollNo, String department) {
        // create a new student record in the database using Hibernate or other ORM framework
        // return true if the student record is created successfully, else false
        return false;
    }
}

