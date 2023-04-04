<%--
  Created by IntelliJ IDEA.
  User: rakahithrk
  Date: 29/03/23
  Time: 7:43 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>
</head>
<body>
<h1>Login</h1>
<c:if test="${not empty error}">
  <div style="color: red;">${error}</div>
</c:if>
<form method="post" action="${pageContext.request.contextPath}/login">
  <label for="username">Username:</label>
  <input type="text" id="username" name="username" required>
  <br>
  <label for="password">Password:</label>
  <input type="password" id="password" name="password" required>
  <br>
  <button type="submit">Login</button>
</form>
<p>Don't have an account? <a href="${pageContext.request.contextPath}/register.jsp">Register</a></p>
</body>
</html>

