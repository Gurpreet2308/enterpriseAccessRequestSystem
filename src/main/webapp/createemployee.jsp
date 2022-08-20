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
<div>
    <h1>Create New Employee.</h1><br/>
    <h2>Fill details below</h2><br/>
    <form action="create-employee" method="post">
        <label id="fname">First Name</label>
        <input type="text" name="fname" placeholder="First Name" required> <br/>
        <label id="lname">Last Name</label>
        <input type="text" name="lname" placeholder="Last Name" required> <br/>
        <label id="email">Email Address</label>
        <input type="text" name="email" placeholder="Email Address" required> <br/>
        <label id="phoneno">Phone Number</label>
        <input type="text" name="phoneno" placeholder="Phone Number" required> <br/>
        <label for="role"><b>Role</b></label>
        <select id="role" name="role">
            <c:forEach begin="0" end="${allRole.size()-1}" var="i">
                <option>${allRole.get(i)}</option>
            </c:forEach>
        </select><br/><br/>
        <label for="dept"><b>Department</b></label>
        <select id="dept" name="dept">
            <c:forEach begin="0" end="${allDept.size()-1}" var="i">
                <option>${allDept.get(i)}</option>
            </c:forEach>
        </select><br/><br/>
        <input type="submit" value="Create">
    </form>
</div>
</body>
</html>
