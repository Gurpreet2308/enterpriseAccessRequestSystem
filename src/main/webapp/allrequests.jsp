<%@ page import="database.Request" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Gurpreet
  Date: 01-09-2022
  Time: 04:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Requests</title>
    <style>
        table{
            margin-left: auto;
            margin-right: auto;
            padding: 20px;
            border-spacing: 20px;
        }
        th{
            border: 1px solid black;
            border-radius: 10px;
            padding-top: 4px;
            padding-bottom: 4px;
            padding-right: 15px;
            padding-left: 15px;
        }
        td {
            text-align: center;
        }
        #accept:hover,#confirmAccept:hover{
            background-color: #AFE1AF;
        }
        #reject:hover, #confirmReject:hover{
            background-color: #F88379;
        }
        #buttons{
            font-size: 20px;
        }
        .popUp{
            border-style: solid;
            border-color: dimgrey;
            border-radius: 10px;
            background-color: white;
            width: 20%;
            height: 20%;
            position: absolute;
            top: 0%;
            left:40%;
            visibility: hidden;
        }
        .openPopUp{
            top: 40%;
            visibility: visible;
        }
    </style>

</head>
<body>
<div>
    <div style="height: 3%; width: 100%">
        <div align="center" style="margin-left: 20px;margin-top: 25px">
            <form action="home-page" method="get">
                <input type="submit" value="Home Page" style="float: left; padding-left: 10px; padding-right: 10px; padding-top: 5px; padding-bottom: 5px">
            </form>
        </div>
    </div>
    <div style="width: 100%; height: 97%">
        <br/><br/>
        <h2 style="text-align: center">All Requests</h2>
        <table>
            <tr>
                <th>Id</th>
                <th>Area Requested</th>
                <th>Created By</th>
                <th>Date of Creation</th>
                <th>Status</th>
            </tr>
            <% ArrayList<Request> allRequests = new ArrayList<Request> ((ArrayList<Request>)request.getAttribute("allRequests"));
                for(int i = 0; i < allRequests.size(); i+=1) { %>
            <tr>
                <form action="request-action" method="get">
                    <td><%=allRequests.get(i).getReqId()%></td> <input type="hidden" name="reqId" value="<%=allRequests.get(i).getReqId()%>">
                    <td><%=allRequests.get(i).getAreaName()%></td> <input type="hidden" name="areaName" value="<%=allRequests.get(i).getAreaName()%>">
                    <td><%=allRequests.get(i).getEmpName()%></td> <input type="hidden" name="empName" value="<%=allRequests.get(i).getEmpName()%>">
                    <td><%=allRequests.get(i).getReqCreatedDate()%></td> <input type="hidden" name="reqCreatedDate" value="<%=allRequests.get(i).getReqCreatedDate()%>">
                    <td><%=allRequests.get(i).getStatus()%></td> <input type="hidden" name="reqStatus" value="<%=allRequests.get(i).getStatus()%>">
                    <%
                        if(allRequests.get(i).getStatus().equals("Pending")){ %>
                    <td id="buttons"><input type="submit" value="Accept" id="accept" name="buttonClicked"> <input value="Reject" type="submit" id="reject" name="buttonClicked"></td>
                    <% } %>
                </form>
            </tr>
            <% } %>
        </table>
    </div>
</div>
<div class="popUp" align="center" id="popUpA">
    <div align="center">
        <h4>Please confirm request action</h4>
        <form action="request-action" method="get">
            <input id="confirmAccept" type="submit" value="Accept">
        </form>
        <button value="Cancel" onclick="closePopUpAccept()">Cancel</button>
    </div>
</div>
<div class="popUp" align="center" id="popUpR">
    <div align="center">
        <h4>Please confirm request action</h4>
        <form action="request-action" method="get">
            <input id="confirmReject" type="submit" value="Reject">
        </form>
        <button value="Cancel" onclick="closePopUpReject()">Cancel</button>
    </div>
</div>
<script>
    let popUpA = document.getElementById("popUpA");
    let popUpR = document.getElementById("popUpR");
    function openPopUpAccept(){
        popUpA.classList.add("openPopUp");
    }
    function openPopUpReject(){
        popUpR.classList.add("openPopUp");
    }
    function closePopUpAccept(){
        popUpA.classList.remove("openPopUp");
    }
    function closePopUpReject(){
        popUpR.classList.remove("openPopUp");
    }
</script>
</body>
</html>
