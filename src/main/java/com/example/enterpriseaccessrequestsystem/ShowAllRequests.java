package com.example.enterpriseaccessrequestsystem;

import application.ApplicationExecution;
import database.Employee;
import database.EmployeeRole;
import database.Request;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/show-all-request")
public class ShowAllRequests extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Employee emp = (Employee)request.getSession().getAttribute("loggedEmployee");
            ArrayList<Request> allRequests = new ArrayList<>();
            allRequests = ApplicationExecution.getAllRequests(emp);
            request.setAttribute("allRequests",allRequests);
            request.getRequestDispatcher("/allrequests.jsp").forward(request,response);
        }catch(Exception e){}

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
