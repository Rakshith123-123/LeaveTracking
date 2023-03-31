package com.boomi.leavetracking.servlet;

import com.boomi.leavetracking.dao.AttendenceDao;
import com.boomi.leavetracking.dao.StudentDao;
import com.boomi.leavetracking.models.Attendance;
import com.boomi.leavetracking.models.Student;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/createAttendance")
public class AddAttendanceServlet extends HttpServlet {

    private AttendenceDao attendanceDao;
    private StudentDao studentDao;

    public void init() {
        attendanceDao = new AttendenceDao();
        studentDao = new StudentDao();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // get the roll no and date from the form submission
        String rollNo = request.getParameter("rollNo");
        String dateStr = request.getParameter("date");
        String attendance = request.getParameter("attendance");

        // add the attendance record to the database
        boolean success = attendanceDao.createAttendence(rollNo,dateStr,attendance);
        if (success) {
            // redirect the user to the attendance.jsp page
            response.sendRedirect("attendance.jsp");
        } else {
            // handle the error appropriately
            // ...
        }
    }
}

