package com.boomi.leavetracking.servlet;

import com.boomi.leavetracking.dao.AttendenceDao;
import com.boomi.leavetracking.models.Attendance;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteAttendance")
public class DeleteAttendanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rollNo = request.getParameter("rollNo");
        String date = request.getParameter("date");
        // delete the attendance record for the specified student on the specified date
        AttendenceDao attendenceDao=new AttendenceDao();
        boolean success = attendenceDao.deleteAttendance(rollNo, date);
        if (success) {
            // redirect to attendance page for the specified student
            List<Attendance> attendanceList = attendenceDao.getAllAttendance();
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("attendanceRecords.jsp");
            request.setAttribute("attendanceList",attendanceList);
            requestDispatcher.forward(request,response);
        } else {
            // show error message and redirect back to attendance page
            request.setAttribute("error", "Failed to delete attendance record");
            request.getRequestDispatcher("/deleteAttendanceForm.jsp").forward(request, response);
        }
    }
}
