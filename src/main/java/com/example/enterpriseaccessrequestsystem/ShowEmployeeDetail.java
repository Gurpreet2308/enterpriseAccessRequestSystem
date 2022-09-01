package com.example.enterpriseaccessrequestsystem;

import application.ApplicationExecution;
import database.Employee;
import database.EmployeeDepartment;
import database.EmployeeRole;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/show-employee-detail")
public class ShowEmployeeDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Employee emp = ApplicationExecution.getLatestEmployee();
            EmployeeRole empRole = ApplicationExecution.getEmpRoleDetails(emp);
            EmployeeDepartment empDept = ApplicationExecution.getEmpDeptDetails(emp);

            request.setAttribute("emp", emp);
            request.setAttribute("empRole",empRole);
            request.setAttribute("empDept", empDept);
            request.getRequestDispatcher("/showemployeedetail.jsp").forward(request, response);
        }catch(Exception e){}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
