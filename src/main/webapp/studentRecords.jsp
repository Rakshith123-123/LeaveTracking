<%@ page import="java.util.List" %>
<%@ page import="com.boomi.leavetracking.models.Student" %><%--
  Created by IntelliJ IDEA.
  User: rakahithrk
  Date: 30/03/23
  Time: 1:45 pm
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
    if(session.getAttribute("username") == null){
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
%>
<%
    List<Student> allStudents = (List<Student>) request.getAttribute("allStudents");
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
        for (Student student : allStudents) {
    %>
    <tr>
        <td><%=student.getId()%></td>
        <td><%=student.getName()%></td>
        <td><%=student.getRollNo()%></td>
        <td><%=student.getDepartment()%></td>
    </tr>
    <%
        }
    %>>
</table>
<br>
<a href="addStudentForm.jsp">Add Student</a>
<br>
<a href="home.jsp">Home</a>
</body>
</html>
