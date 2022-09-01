<%--
  Created by IntelliJ IDEA.
  User: Gurpreet
  Date: 28-08-2022
  Time: 10:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Request</title>
</head>
<body>
<div style="height: 3%; width: 100%">
    <div align="center" style="margin-left: 20px;margin-top: 25px">
        <form action="home-page" method="get">
            <input type="submit" value="Home Page" style="float: left; padding-left: 10px; padding-right: 10px; padding-top: 5px; padding-bottom: 5px">
        </form>
    </div>
</div>
<div align = "center" style="width: 100%; height: 97%;">
    <h2>Create New Request</h2>
    <h3>Select Area below</h3><br/>
    <form action="create-request" method="post">
        <label for="area">Area</label>&emsp;
        <select id="area" name="area">
            <c:forEach begin="0" end="${allArea.size()-1}" var="i">
                <option>${allArea.get(i)}</option>
            </c:forEach>
        </select><br/><br/><br/>
        <input type="submit" value="Create" style="padding-left: 10px; padding-right: 10px; padding-top: 5px; padding-bottom: 5px">
    </form>
</div>
</body>
</html>
