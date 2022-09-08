package com.example.enterpriseaccessrequestsystem;

import application.ApplicationExecution;
import database.Area;
import database.Employee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/my-area")
public class MyArea extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Employee emp = (Employee) request.getSession().getAttribute("loggedEmployee");
            ArrayList<Area> empAreas = ApplicationExecution.getEmployeeAreas(emp);
            request.setAttribute("empAreas", empAreas);
            request.getRequestDispatcher("/myarea.jsp").forward(request,response);
        }catch (Exception e){}

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
