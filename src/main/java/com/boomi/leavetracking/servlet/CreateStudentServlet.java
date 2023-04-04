package com.boomi.leavetracking.servlet;

import com.boomi.leavetracking.dao.StudentDao;
import com.boomi.leavetracking.models.Student;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
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
        StudentDao studentDao = new StudentDao();
        boolean success = studentDao.createStudent(name, rollNo, department);
        if (success) {
            List<Student> allStudents = studentDao.getAllStudents();
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("studentRecords.jsp");
            request.setAttribute("allStudents",allStudents);
            requestDispatcher.forward(request,response);
            // redirect to student records page
            // response.sendRedirect(request.getContextPath() + "/studentRecords.jsp");
        } else {
            // show error message and redirect back to create student form
            request.setAttribute("error", "Failed to create student record");
            request.getRequestDispatcher("/addStudentForm.jsp").forward(request, response);
        }
    }
}

