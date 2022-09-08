<%@ page import="database.Request" %>
<%@ page import="database.Employee" %><%--
  Created by IntelliJ IDEA.
  User: Gurpreet
  Date: 29-08-2022
  Time: 05:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Request Created!</title>
</head>
<body>
<%
    Request req = (Request) request.getAttribute("req");
    Employee emp = (Employee) request.getAttribute("emp");
%>
<div style="height: 3%; width: 100%">
    <div align="center" style="margin-left: 20px;margin-top: 25px">
        <form action="home-page" method="get">
            <input type="submit" value="Home Page" style="float: left; padding-left: 10px; padding-right: 10px; padding-top: 5px; padding-bottom: 5px">
        </form>
    </div>
</div>
<div align="center" style="margin-top: 40px; height: 97%; width: 100%">
    <h2>New Request Created Successfully with Request Id : <%=req.getReqId()%></h2><br/>
    <div align="center" style="font-size: 17px">
        <table style="width: 30%; height: 40%; font-size: 17px; padding-left: 50px">
            <tr>
                <td><label>Request Id : </label> </td>
                <td><%=req.getReqId()%></td>
            </tr>
            <tr>
                <td><label>Created By :</label></td>
                <td><%=emp.getEmpFirstName()%> <%=emp.getEmpLastName()%></td>
            </tr>
            <tr>
                <td><label>Date Created :</label> </td>
                <td><%=req.getReqCreatedDate()%></td>
            </tr>
            <tr>
                <td><label>Area Requested :</label> </td>
                <td><%=req.getAreaName()%></td>
            </tr>
            <tr>
                <td><label>Status :</label></td>
                <td>In Process</td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
