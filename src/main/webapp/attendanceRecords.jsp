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
  <title>Student Records</title>
</head>
<body>
<%
  List<Attendance> attendanceList = (List<Attendance>) request.getAttribute("attendanceList");
%>
<h1>Student Records</h1>
<table>
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Roll No</th>
    <th>Department</th>
  </tr>
  <%
    for (Attendance attendance : attendanceList) {
  %>
  <tr>
    <td><%=attendance.getId()%></td>
    <td><%=attendance.getDate()%></td>
    <td><%=attendance.getStudent()%></td>
    <td><%=attendance.getAttendance()%></td>
<%--    <td>--%>
<%--    <a href="updateAttendanceForm?rollNo=${record.rollNo}&amp;date=${record.date}">Update</a>--%>
<%--    <a href="deleteAttendance?rollNo=${record.rollNo}&amp;date=${record.date}">Delete</a>--%>
<%--    <td>--%>
  </tr>
  <%
    }
  %>>
</table>
<br>
<a href="addAttendanceForm.jsp">Add Student</a>
</body>
</html>
