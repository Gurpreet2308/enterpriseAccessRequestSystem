package com.example.enterpriseaccessrequestsystem;

import application.ApplicationExecution;
import database.Employee;
import database.EmployeeDepartment;
import database.EmployeeRole;
import database.Request;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/show-request-detail")
public class ShowRequestDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Request req = ApplicationExecution.getLatestRequest();
            Employee emp = (Employee)request.getSession().getAttribute("loggedEmployee");

            request.setAttribute("emp", emp);
            request.setAttribute("req", req);
            request.getRequestDispatcher("/showrequestdetail.jsp").forward(request, response);
        }catch(Exception e){}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
