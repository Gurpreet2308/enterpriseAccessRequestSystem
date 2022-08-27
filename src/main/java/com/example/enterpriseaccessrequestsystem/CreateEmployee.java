package com.example.enterpriseaccessrequestsystem;

import application.ApplicationExecution;
import database.Employee;
import database.EmployeeDepartment;
import database.EmployeeRole;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet("/create-employee")
public class CreateEmployee extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            ArrayList<String> allDept = new ArrayList<>(ApplicationExecution.getAllDept());
            ArrayList<String> allRole = new ArrayList<>(ApplicationExecution.getAllRoles());

            request.setAttribute("allDept",allDept);
            request.setAttribute("allRole",allRole);
            request.getRequestDispatcher("/createemployee.jsp").forward(request,response);
        }catch (Exception e){}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Employee emp = new Employee();
            EmployeeRole empRole = new EmployeeRole();
            EmployeeDepartment empDept = new EmployeeDepartment();

            emp.setEmpFirstName(request.getParameter("fname"));
            emp.setEmpLastName(request.getParameter("lname"));
            emp.setEmpEmailAddress(request.getParameter("email"));
            emp.setEmpPhoneNo(request.getParameter("phoneno"));

            empRole.setRoles(new ArrayList<>(Collections.singletonList(request.getParameter("role"))));
            empDept.setDeptName(request.getParameter("dept"));

            String empUsername = ApplicationExecution.createEmployee(emp, empRole, empDept);
            if(!empUsername.isEmpty() && empUsername!=null){//!empUsername.isEmpty() && empUsername!=null
                request.getSession().setAttribute("taskExecuted", (int)request.getSession().getAttribute("taskExecuted")+1);
                response.sendRedirect("show-employee-detail");
            }
        }catch(Exception e){}
    }
}
