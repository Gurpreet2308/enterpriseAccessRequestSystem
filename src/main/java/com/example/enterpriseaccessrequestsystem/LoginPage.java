package com.example.enterpriseaccessrequestsystem;

import application.ApplicationExecution;
import database.Employee;
import database.EmployeeRole;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet("/login-page")
public class LoginPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher r = getServletContext().getRequestDispatcher("/loginpage.jsp");
        request.setAttribute("success","True");
        r.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{

            String userName = request.getParameter("username");
            String password = request.getParameter("password");

            Employee loginEmp = ApplicationExecution.checkLogin(userName,password);

            if(loginEmp!=null){
                EmployeeRole loginEmpRole = ApplicationExecution.getEmployeeLoggedInRole(userName, password);
                loginEmp.setEmpUserName(userName);
                String firstName = ApplicationExecution.getEmployeeFirstName(userName);
                if(!firstName.isEmpty()){
                    loginEmp.setEmpFirstName(firstName);
                }

                HttpSession session = request.getSession();
                session.setAttribute("loggedEmployee", loginEmp);
                session.setAttribute("loggedEmployeeRole", loginEmpRole);
                session.setAttribute("taskExecuted", 0);
                //session.setMaxInactiveInterval(60*30);//session timeout for 30 minutes

                ApplicationExecution.setLoginAuditEntry(loginEmp,loginEmpRole);

                response.sendRedirect("home-page");


                /*String empLoggedInRole = ApplicationExecution.getEmployeeLoggedInRole(userName, password);
                if(empLoggedInRole!=null){
                    List<String> uiButtons = ApplicationExecution.getRoleUIButtons(empLoggedInRole);
                    if(!uiButtons.isEmpty()){
                        request.setAttribute("buttons",uiButtons);
                    }
                }
                Employee emp = new Employee();
                emp.setEmpUserName(userName);
                String firstName = ApplicationExecution.getEmployeeFirstName(userName);
                if(!firstName.isEmpty()){
                    emp.setEmpFirstName(firstName);
                }
                EmployeeRole empRole = new EmployeeRole();
                empRole.setRoles(new ArrayList<String>(Collections.singleton(empLoggedInRole)));
                request.setAttribute("employee",emp);
                request.setAttribute("employeeRole",empRole);

                HttpSession session = request.getSession();
                session.setAttribute("loggedEmployee", emp);
                session.setAttribute("loggedEmployeeRole", empRole);

                //ApplicationExecution.setLoginAuditEntry(emp,empRole);

                request.getRequestDispatcher("/homepage.jsp").forward(request, response);*/
            }
            else{
                request.setAttribute("success","False");
                request.getRequestDispatcher("/loginpage.jsp").forward(request, response);
            }
        }catch (Exception e){}
    }
}
