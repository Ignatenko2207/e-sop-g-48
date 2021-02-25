<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>My e-shop</title>
</head>
<body>
<h2><c:out value="${message}"/></h2>
<br>

<form action="/authorization" method="post">
    <input type="text" size="40" name="login" placeholder="Input login" required>
    <br>
    <input type="password" size="40" name="password" placeholder="Input password" required>
    <br>
    <input type="submit" value="LOGIN">
</form>
<br>
<h3>
    <a href="/jsp/registration.jsp">REGISTER NEW USER</a>
</h3>
</body>
</html>
