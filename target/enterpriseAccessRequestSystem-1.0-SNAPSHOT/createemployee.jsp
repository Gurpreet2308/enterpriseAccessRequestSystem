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
        <table style="width: 25%; height: 40%; font-size: 16px; padding-left: 50px">
            <tr>
                <td><label id="fname">First Name</label></td>
                <td><input type="text" name="fname" placeholder="First Name" required></td>
            </tr>
            <tr>
                <td><label id="lname">Last Name</label></td>
                <td><input type="text" name="lname" placeholder="Last Name" required> <br/> </td>
            </tr>
            <tr>
                <td><label id="email">Email Address</label></td>
                <td><input type="text" name="email" placeholder="Email Address" required></td>
            </tr>
            <tr>
                <td><label id="phoneno">Phone Number</label></td>
                <td><input type="text" name="phoneno" placeholder="Phone Number" required></td>
            </tr>
            <tr>
                <td><label for="role">Role</label></td>
                <td><select id="role" name="role">
                    <c:forEach begin="0" end="${allRole.size()-1}" var="i">
                        <option>${allRole.get(i)}</option>
                    </c:forEach>
                </select></td>
            </tr>
            <tr>
                <td><label for="dept">Department</label></td>
                <td><select id="dept" name="dept">
                    <c:forEach begin="0" end="${allDept.size()-1}" var="i">
                        <option>${allDept.get(i)}</option>
                    </c:forEach>
                </select</td>
            </tr>
        </table><br/><br/><br/><br/>
        <input type="submit" value="Create" style="padding-left: 10px; padding-right: 10px; padding-top: 5px; padding-bottom: 5px">
    </form>
</div>
</body>
</html>
