<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="database.Area" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.lang.reflect.Array" %><%--
  Created by IntelliJ IDEA.
  User: Gurpreet
  Date: 08-09-2022
  Time: 07:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Areas!</title>
</head>
<body>
<div style="height: 3%; width: 100%">
    <div align="center" style="margin-left: 20px;margin-top: 25px">
        <form action="home-page" method="get">
            <input type="submit" value="Home Page" style="float: left; padding-left: 10px; padding-right: 10px; padding-top: 5px; padding-bottom: 5px">
        </form>
    </div>
</div>
<div align = "center" style="margin-top: 40px;width: 100%; height: 97%;">
  <%
      ArrayList<Area> empAreas = new ArrayList<Area>((ArrayList<Area>)request.getAttribute("empAreas"));
  %>
    <%
        if(empAreas.isEmpty()){
    %>
    <h2>Sorry! you don't have any access areas. Try creating a request.</h2>
    <%
        }
        else{
    %>
    <h2>You have access to the following areas:</h2>
    <table style="font-size: 17px;">
        <c:forEach begin="0" end="${empAreas.size()-1}" var="i">
            <tr><td>${empAreas.get(i).getAreaName()}</td></tr>
        </c:forEach>
    </table>
    <%}%>
</div>
</body>
</html>
