<%@ page import="database.EmployeeRole" %>
<%@ page import="java.sql.Timestamp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Gurpreet
  Date: 08-08-2022
  Time: 06:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modify access here</title>
</head>
<body>
<div style="height: 3%; width: 100%">
    <div align="center" style="margin-left: 20px;margin-top: 25px">
        <form action="home-page" method="get">
            <input type="submit" value="Home Page" style="float: left; padding-left: 10px; padding-right: 10px; padding-top: 5px; padding-bottom: 5px">
        </form>
    </div>
</div>
<div align="center" style="width: 100%; height: 97%;">
    <h2>Modify access here</h2><br/>
    <%
        EmployeeRole emp = new EmployeeRole();
        emp.setEmpName(request.getParameter("empName"));
        emp.setRoleStartDate(Timestamp.valueOf(request.getParameter("roleStartDate")));
        request.setAttribute("empRoleDet",emp);
    %>
    <form action="configure-access" method="post">
        <label id="empName">Employee Name</label>&emsp;
        <input type="text" name="empName" value="${empName}" required><br/><br/>
        <label for="role">Role</label>&emsp;
        <select id="role" name="role">
            <option selected>${role}</option>
            <c:forEach begin="0" end="${roles.size()-1}" var="i">
                <option>${roles.get(i)}</option>
            </c:forEach>
        </select><br/><br/>
        <label id="roleStartDate">Start Date</label>&emsp;
        <input type="datetime-local" name="roleStartDate" value="${roleStartDate}" required><br/><br/>
        <label id="roleEndDate">End Date</label>&emsp;
        <input type="datetime-local" name="roleEndDate" value="${roleEndDate}"><br/><br/><br/>
        <input type="submit" value="Configure" style="padding-left: 10px; padding-right: 10px; padding-top: 5px; padding-bottom: 5px" />
    </form>
</div>
</body>
</html>
