package com.boomi.leavetracking.servlet;

import com.boomi.leavetracking.dao.StudentDao;
import com.boomi.leavetracking.models.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/showStudents")
public class ShowStudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentDao studentDao = new StudentDao();
        List<Student> allStudents = studentDao.getAllStudents();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("studentRecords.jsp");
        request.setAttribute("allStudents",allStudents);
        requestDispatcher.forward(request,response);
    }
}
