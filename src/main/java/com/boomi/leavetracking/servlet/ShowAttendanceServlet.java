package com.boomi.leavetracking.servlet;

import com.boomi.leavetracking.dao.AttendenceDao;
import com.boomi.leavetracking.dao.StudentDao;
import com.boomi.leavetracking.models.Attendance;
import com.boomi.leavetracking.models.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/showAttendance")
public class ShowAttendanceServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AttendenceDao attendenceDao = new AttendenceDao();
        List<Attendance> attendanceList = attendenceDao.getAllAttendance();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("attendanceRecords.jsp");
        request.setAttribute("attendanceList",attendanceList);
        requestDispatcher.forward(request,response);
    }
}
