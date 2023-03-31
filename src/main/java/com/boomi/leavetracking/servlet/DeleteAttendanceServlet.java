package com.boomi.leavetracking.servlet;

import com.boomi.leavetracking.dao.AttendenceDao;

import java.io.IOException;
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
            response.sendRedirect(request.getContextPath() + "/attendance.jsp?rollNo=" + rollNo);
        } else {
            // show error message and redirect back to attendance page
            request.setAttribute("error", "Failed to delete attendance record");
            request.getRequestDispatcher("/attendance.jsp?rollNo=" + rollNo).forward(request, response);
        }
    }
}
