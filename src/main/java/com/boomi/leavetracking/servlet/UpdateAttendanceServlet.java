package com.boomi.leavetracking.servlet;

import com.boomi.leavetracking.dao.AttendenceDao;

import java.io.IOException;
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
        AttendenceDao attendenceDao = new AttendenceDao();
        boolean success = attendenceDao.updateAttendance(rollNo, date, attendance);
        if (success) {
            // redirect to attendance page for the specified student
            response.sendRedirect(request.getContextPath() + "/attendance.jsp?rollNo=" + rollNo);
        } else {
            // show error message and redirect back to update attendance form
            request.setAttribute("error", "Failed to update attendance record");
            request.getRequestDispatcher("/updateAttendance.jsp").forward(request, response);
        }
    }
}
