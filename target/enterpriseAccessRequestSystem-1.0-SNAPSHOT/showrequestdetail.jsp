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
        <label>Request Id : </label> <%=req.getReqId()%> <br/><br/>
        <label>Created By :</label> <%=emp.getEmpFirstName()%> <%=emp.getEmpLastName()%><br/><br/>
        <label>Date Created :</label> <%=req.getReqCreatedDate()%> <br/><br/>
        <label>Area Requested :</label> <%=req.getAreaName()%> <br/><br/>
        <label>Status :</label> In Process <br/><br/>
    </div>
</div>
</body>
</html>
