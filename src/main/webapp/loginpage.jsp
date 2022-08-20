<%--
  Created by IntelliJ IDEA.
  User: Gurpreet
  Date: 25-07-2022
  Time: 01:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Login</title>
</head>
<body>
<div align="center">
    <h2>Enter Credentials</h2>
</div>
<br/><br/>
<div align="center">
    <form action="login-page" method="post">
        <label id="username"><b>Username &emsp;</b></label>
        <input type="text" name="username" placeholder="User Name" required><br/><br/>
        <label id="password"><b>Password &emsp;</b></label>
        <input type="password" name="password" placeholder="Password" required><br/><br/><br/>
        <c:if test="${success =='False'}">
            <h5 style="color: red; "><b>Please Enter valid credentials</b></h5><br/>
        </c:if>
        <input align="center" type="submit" value="Login">
    </form>
</div>
</body>
</html>
