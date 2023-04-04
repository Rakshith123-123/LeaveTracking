package com.boomi.leavetracking.servlet;

import com.boomi.leavetracking.dao.AttendenceDao;
import com.boomi.leavetracking.models.Attendance;
import com.boomi.leavetracking.models.Student;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateAttendance")
public class UpdateAttendanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rollNo = request.getParameter("rollNo");
        String date = request.getParameter("date");
        String attendance = request.getParameter("attendance");
        // update the attendance record for the specified student on the specified date
        AttendenceDao attendanceDao = new AttendenceDao();
        boolean success = attendanceDao.updateAttendance(rollNo, date, attendance);
        if (success) {
            // redirect to attendance page for the specified student
            List<Attendance> attendanceList = attendanceDao.getAllAttendance();
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("attendanceRecords.jsp");
            request.setAttribute("attendanceList",attendanceList);
            requestDispatcher.forward(request,response);
        } else {
            // show error message and redirect back to update attendance form
            request.setAttribute("error", "Failed to update attendance record");
            request.getRequestDispatcher("/addUpdateAttendanceForm.jsp").forward(request, response);
        }
    }
}
