<%@ page import="database.Employee" %>
<%@ page import="database.EmployeeRole" %>
<%@ page import="database.EmployeeDepartment" %><%--
  Created by IntelliJ IDEA.
  User: Gurpreet
  Date: 26-08-2022
  Time: 02:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee Created!</title>
</head>
<style>
    p{

    }
    .dic1{

    }
</style>
<body>
<%
    Employee emp = (Employee)request.getAttribute("emp");
    EmployeeRole empRole = (EmployeeRole) request.getAttribute("empRole");
    EmployeeDepartment empDept = (EmployeeDepartment) request.getAttribute("empDept");
%>
<div style="height: 3%; width: 100%">
    <div align="center" style="margin-left: 20px;margin-top: 25px">
        <form action="home-page" method="get">
            <input type="submit" value="Home Page" style="float: left; padding-left: 10px; padding-right: 10px; padding-top: 5px; padding-bottom: 5px">
        </form>
    </div>
</div>
<div align="center" style="margin-top: 40px; height: 97%; width: 100%">
    <h2>New Employee Created Successfully with Username : <%=emp.getEmpUserName()%></h2><br/>
    <div align="center" style="font-size: 17px">
        <label>First Name : </label> <%=emp.getEmpFirstName()%> <br/><br/>
        <label>Last Name :</label> <%=emp.getEmpLastName()%> <br/><br/>
        <label>User Name :</label> <%=emp.getEmpUserName()%> <br/><br/>
        <label>Department :</label> <%=empDept.getDeptName()%> <br/><br/>
        <label>Role :</label> <%=empRole.getRoles().get(0)%> <br/><br/>
        <label>Email Address :</label> <%=emp.getEmpEmailAddress()%> <br/><br/>
        <label>Phone No :</label> <%=emp.getEmpPhoneNo()%>
    </div>
</div>
</body>
</html>
