<%@ page import="com.boomi.leavetracking.models.Attendance" %>
<%@ page import="java.util.List" %>
<%@ page import="com.boomi.leavetracking.models.Student" %><%--
  Created by IntelliJ IDEA.
  User: rakahithrk
  Date: 30/03/23
  Time: 7:00 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Attendance Records</title>
</head>
<body>
<%
  if(session.getAttribute("username") == null){
    response.sendRedirect(request.getContextPath() + "/login.jsp");
  }
%>
<%
  List<Attendance> attendanceList = (List<Attendance>) request.getAttribute("attendanceList");
%>
<h1>Attendance Records</h1>
<table>
  <tr>
    <th>ID</th>
    <th>Date</th>
    <th>Roll No</th>
    <th>Present/Absent</th>
  </tr>
  <%
    for (Attendance attendance : attendanceList) {
  %>
  <tr>
    <td><%=attendance.getId()%></td>
    <td><%=attendance.getDate()%></td>
    <td><%=attendance.getStudent()%></td>
    <td><%=attendance.getAttendance()%></td>
    <td>
    <a href="addUpdateAttendanceForm.jsp">Update</a>
    <a href="deleteAttendanceForm.jsp">Delete</a>
    <td>
  </tr>
  <%
    }
  %>>
</table>
<br>
<a href="addAttendanceForm.jsp">Add Attendance</a>
<br>
<a href="home.jsp">Home</a>
</body>
</html>
