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
<h2>Modify access here.</h2>
<%
    EmployeeRole emp = new EmployeeRole();
    emp.setEmpName(request.getParameter("empName"));
    emp.setRoleStartDate(Timestamp.valueOf(request.getParameter("roleStartDate")));
    request.setAttribute("empRoleDet",emp);
%>
<form action="configure-access" method="post">
    <label id="empName"><b>Employee Name &emsp;</b></label>
    <input type="text" name="empName" value="${empName}" required><br/><br/>
    <label for="role"><b>Role</b></label>
    <select id="role" name="role">
        <option selected>${role}</option>
        <c:forEach begin="0" end="${roles.size()-1}" var="i">
            <option>${roles.get(i)}</option>
        </c:forEach>
    </select><br/><br/>
    <label id="roleStartDate"><b>Start Date &emsp;</b></label>
    <input type="datetime-local" name="roleStartDate" value="${roleStartDate}" required><br/><br/>
    <label id="roleEndDate"><b>End Date &emsp;</b></label>
    <input type="datetime-local" name="roleEndDate" value="${roleEndDate}"><br/><br/>
    <input type="submit" value="Configure" />
</form>
</body>
</html>
