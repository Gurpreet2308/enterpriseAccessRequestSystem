package com.example.enterpriseaccessrequestsystem;

import application.ApplicationExecution;
import database.EmployeeRole;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/all-employee-access")
public class AllEmployeeAccess extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            ArrayList<EmployeeRole> allEmpRolesDetails = new ArrayList<>();
            allEmpRolesDetails = ApplicationExecution.getAllEmpRolesDetails();
            request.setAttribute("allEmployeesRoleDetails",allEmpRolesDetails);
            request.setAttribute("emp", request.getAttribute("emp"));
            request.setAttribute("empRole",request.getAttribute("empRole"));
            request.getRequestDispatcher("/allemployeeaccess.jsp").forward(request,response);
        }catch(Exception e){}
/*
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1> clicked on configure access </h1>");
        out.println("</body></html>");*/

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            /*String empName = request.getParameter("empName");
            String role = request.getParameter("role");
            String roleStartDate = request.getParameter("roleStartDate");
            String roleEndDate = request.getParameter("roleEndDate");

            request.setAttribute("empName",request.getParameter("empName"));
            request.setAttribute("empName",request.getParameter("role"));
            request.setAttribute("empName",request.getParameter("role"));
            request.setAttribute("empName",request.getParameter("empName"));
            System.out.println(empName);*/
        }catch(Exception e){}

    }
}
