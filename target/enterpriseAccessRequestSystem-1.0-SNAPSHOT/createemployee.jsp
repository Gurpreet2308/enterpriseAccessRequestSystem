<%--
  Created by IntelliJ IDEA.
  User: Gurpreet
  Date: 14-08-2022
  Time: 02:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Employee</title>
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
    <h2>Create New Employee</h2>
    <h3>Fill details below</h3><br/>
    <form action="create-employee" method="post">
        <label id="fname">First Name</label>&emsp;
        <input type="text" name="fname" placeholder="First Name" required> <br/> <br/>
        <label id="lname">Last Name</label>&emsp;
        <input type="text" name="lname" placeholder="Last Name" required> <br/> <br/>
        <label id="email">Email Address</label>&emsp;
        <input type="text" name="email" placeholder="Email Address" required> <br/> <br/>
        <label id="phoneno">Phone Number</label>&emsp;
        <input type="text" name="phoneno" placeholder="Phone Number" required> <br/> <br/>
        <label for="role">Role</label>&emsp;
        <select id="role" name="role">
            <c:forEach begin="0" end="${allRole.size()-1}" var="i">
                <option>${allRole.get(i)}</option>
            </c:forEach>
        </select><br/><br/>
        <label for="dept">Department</label>&emsp;
        <select id="dept" name="dept">
            <c:forEach begin="0" end="${allDept.size()-1}" var="i">
                <option>${allDept.get(i)}</option>
            </c:forEach>
        </select><br/><br/><br/><br/>
        <input type="submit" value="Create" style="padding-left: 10px; padding-right: 10px; padding-top: 5px; padding-bottom: 5px">
    </form>
</div>
</body>
</html>
