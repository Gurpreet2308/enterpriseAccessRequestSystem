<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Gurpreet
  Date: 26-07-2022
  Time: 05:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<div style="width: 100%; height: 25%">
    <div style="width: 90%; height: 100%; margin-left: 40px; margin-top: 30px">
        <h2>Welcome ${employee.getEmpFirstName()}!</h2>
        <h3>Working as: ${employeeRole.getRoles().get(0)}</h3>
    </div>
</div>
<div style="width: 100%; height: 55%">
    <div align="center" >
        <form action="home-page" method="post">
            <c:forEach begin="0" end="${buttons.size()-1}" var="i">
                <input type="submit" value="${buttons.get(i)}" name="buttonClicked" style="padding: 10px;margin: 10px">
            </c:forEach>
        </form>
    </div>
</div>
<div style="height: 20%; width: 100%">
    <div align="center">
        <form action="logoff-page" method="get">
            <input type="submit" value="LogOff" style="padding-left: 10px; padding-right: 10px; padding-top: 5px; padding-bottom: 5px">
        </form>
    </div>
</div>
</body>
</html>
