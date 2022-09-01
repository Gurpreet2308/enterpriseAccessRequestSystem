package com.example.enterpriseaccessrequestsystem;

import application.ApplicationExecution;
import database.Employee;
import database.EmployeeRole;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/logoff-page")
public class LogoffPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Employee emp = (Employee) request.getSession().getAttribute("loggedEmployee");
            EmployeeRole empRole = (EmployeeRole) request.getSession().getAttribute("loggedEmployeeRole");
            int taskExecuted = (int) request.getSession().getAttribute("taskExecuted");

            boolean success = ApplicationExecution.updateLoginEntry(emp, empRole, taskExecuted);
            if(success){
                request.getSession().removeAttribute("loggedEmployee");
                request.getSession().removeAttribute("loggedEmployeeRole");
                request.getSession().removeAttribute("taskExecuted");
                request.getRequestDispatcher("login-page").forward(request,response);
            }
        }catch (Exception e){}

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
