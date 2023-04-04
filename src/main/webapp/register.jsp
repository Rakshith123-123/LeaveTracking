<%--
  Created by IntelliJ IDEA.
  User: rakahithrk
  Date: 29/03/23
  Time: 7:51 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>
<body>
<h1>Register</h1>
<c:if test="${not empty error}">
    <div style="color: red;">${error}</div>
</c:if>
<form method="post" action="${pageContext.request.contextPath}/register">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required>
    <br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <br>
<%--    <label for="confirmPassword">Confirm Password:</label>--%>
<%--    <input type="password" id="confirmPassword" name="confirmPassword" required>--%>
    <br>
    <button type="submit">Register</button>
</form>
<p>Already have an account? <a href="${pageContext.request.contextPath}/login.jsp">Login</a></p>
</body>
</html>
