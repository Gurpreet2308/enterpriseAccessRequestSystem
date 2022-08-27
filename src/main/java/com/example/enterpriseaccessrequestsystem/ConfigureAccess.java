package com.example.enterpriseaccessrequestsystem;

import application.ApplicationExecution;
import database.EmployeeRole;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet("/configure-access")
public class ConfigureAccess extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            request.setAttribute("empName",request.getParameter("empName"));
            request.setAttribute("role",request.getParameter("role"));
            request.setAttribute("roleStartDate",request.getParameter("roleStartDate"));
            request.setAttribute("roleEndDate",request.getParameter("roleEndDate"));

            ArrayList<String> roles = new ArrayList<String>(ApplicationExecution.getAllRoles());
            if(roles!=null && !roles.isEmpty()){
                roles.remove(roles.indexOf(request.getParameter("role")));
                request.setAttribute("roles", roles);
            }
            request.getRequestDispatcher("/configureaccess.jsp").forward(request,response);
        }catch(Exception e){}

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            EmployeeRole empRole = new EmployeeRole();
            String endDate = request.getParameter("roleEndDate");

            empRole.setRoles(new ArrayList<>(Collections.singleton(request.getParameter("role"))));
            empRole.setRoleStartDate(Timestamp.valueOf(LocalDateTime.parse(request.getParameter("roleStartDate"))));
            if(endDate.isEmpty()){
                empRole.setRoleEndDate(null);
            }
            else{
                empRole.setRoleEndDate(Timestamp.valueOf(LocalDateTime.parse(request.getParameter("roleEndDate"))));
            }
            String empName = request.getParameter("empName");

            boolean success = ApplicationExecution.modifyAccessForEmployee(empName,empRole);
            System.out.println(success);
            if(success){
                request.getSession().setAttribute("taskExecuted", (int)request.getSession().getAttribute("taskExecuted")+1);
                response.sendRedirect("all-employee-access");
                //back to all employee role page
            }
            else{
                //error message
            }
        }catch(Exception e){}
    }
}
