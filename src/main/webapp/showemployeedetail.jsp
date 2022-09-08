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
        <table style="width: 30%; height: 40%; font-size: 17px; padding-left: 50px">
            <tr>
                <td><label>First Name :</label></td>
                <td><%=emp.getEmpFirstName()%></td>
            </tr>
            <tr>
                <td><label>Last Name :</label>;</td>
                <td><%=emp.getEmpLastName()%></td>
            </tr>
            <tr>
                <td><label>User Name :</label></td>
                <td><%=emp.getEmpUserName()%></td>
            </tr>
            <tr>
                <td><label>Department :</label></td>
                <td><%=empDept.getDeptName()%></td>
            </tr>
            <tr>
                <td><label>Role :</label></td>
                <td><%=empRole.getRoles().get(0)%></td>
            </tr>
            <tr>
                <td><label>Email Address :</label></td>
                <td><%=emp.getEmpEmailAddress()%></td>
            </tr>
            <tr>
                <td><label>Phone No :</label></td>
                <td><%=emp.getEmpPhoneNo()%></td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
