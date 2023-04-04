<%--
  Created by IntelliJ IDEA.
  User: rakahithrk
  Date: 30/03/23
  Time: 1:42 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Add Student</title>
</head>
<body>
<%
  if(session.getAttribute("username") == null){
    response.sendRedirect(request.getContextPath() + "/login.jsp");
  }
%>
<h1>Add Student</h1>
<form method="post" action="createStudent">
  <label>Name:</label>
  <input type="text" name="name" required><br>
  <label>Roll No:</label>
  <input type="text" name="rollNo" required><br>
  <label>Department:</label>
  <input type="text" name="department" required><br>
  <button type="submit">Add</button>
</form>
<br>
<a href="home.jsp">Home</a>
</body>
</html>

