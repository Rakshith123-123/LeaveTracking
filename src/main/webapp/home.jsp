<%--
  Created by IntelliJ IDEA.
  User: rakahithrk
  Date: 30/03/23
  Time: 10:23 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Home</title>
</head>
<body>
<%
    if(session.getAttribute("username") == null){
      response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
%>
<h1>Welcome to Attendance Management System</h1>
<ul>
  <li><a href="addStudentForm.jsp">Add Student</a></li>
  <li><a href="addAttendanceForm.jsp">Add Attendance</a></li>
  <li><a href="<%=request.getContextPath()%>/showStudents">Show Students</a></li>
  <li><a href="<%=request.getContextPath()%>/showAttendance">Show Attendance</a></li>
</ul>
</body>
</html>

