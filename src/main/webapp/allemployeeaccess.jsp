<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Gurpreet
  Date: 01-08-2022
  Time: 01:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="database.EmployeeRole" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>All Accesses</title>
    <style>
        table{
            width: 50%;
            height: 50%;
            margin-left: auto;
            margin-right: auto;
            border-spacing: 20px;
        }
        tr,th{
            border: 1px solid black;
            border-radius: 10px;
        }
        td {
            text-align: center;
        }
    </style>
</head>
<body>
<div>
    <div style="width: 100%; height: 100%">
        <br/><br/>
        <h1 style="text-align: center">Employees and their Access</h1>
        <table>
            <tr>
                <th>Employee</th>
                <th>Role</th>
                <th>Role Start Date</th>
                <th>Role End Date</th>
            </tr>
            <% ArrayList<EmployeeRole> allEmployeesRoleDetails = new ArrayList<EmployeeRole> ((ArrayList<EmployeeRole>)request.getAttribute("allEmployeesRoleDetails"));
                for(int i = 0; i < allEmployeesRoleDetails.size(); i+=1) { %>
            <tr>
                <form action="configure-access" method="get">
                    <td><%=allEmployeesRoleDetails.get(i).getEmpName()%></td> <input type="hidden" name="empName" value="<%=allEmployeesRoleDetails.get(i).getEmpName()%>">
                    <td><%=allEmployeesRoleDetails.get(i).getRoles().get(0)%></td> <input type="hidden" name="role" value="<%=allEmployeesRoleDetails.get(i).getRoles().get(0)%>">
                    <td><%=allEmployeesRoleDetails.get(i).getRoleStartDate()%></td> <input type="hidden" name="roleStartDate" value="<%=allEmployeesRoleDetails.get(i).getRoleStartDate()%>">
                    <td><%=allEmployeesRoleDetails.get(i).getRoleEndDate()%></td> <input type="hidden" name="roleEndDate" value="<%=allEmployeesRoleDetails.get(i).getRoleEndDate()%>">
                    <td><input type="submit" value="Configure"></td>
                </form>
            </tr>
            <% } %>
        </table>
    </div>
</div>
</body>
</html>
