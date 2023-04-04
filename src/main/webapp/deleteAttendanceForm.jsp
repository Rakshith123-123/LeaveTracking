<%--
  Created by IntelliJ IDEA.
  User: rakahithrk
  Date: 30/03/23
  Time: 11:36 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Delete Attendance Form</title>
</head>
<body>
<%
    if(session.getAttribute("username") == null){
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
%>
<h1>Delete Attendance</h1>
<form  method="post" action="deleteAttendance">
    <label for="rollNo">Roll No:</label>
    <input type="text" id="rollNo" name="rollNo" required>
    <br>
    <label for="date">Date:</label>
    <input type="date" id="date" name="date" required>
    <br>
<%--    <label for="attendance">Attendance:</label>--%>
<%--    <select id="attendance" name="attendance" required>--%>
<%--        <option value="">--Select--</option>--%>
<%--        <option value="Present">Present</option>--%>
<%--        <option value="Absent">Absent</option>--%>
<%--    </select>--%>
    <br>
    <input type="submit" value="Delete Attendance">
</form>
<br>
<%--<a href="home.jsp">Home</a>--%>
</body>
</html>
