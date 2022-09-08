package com.example.enterpriseaccessrequestsystem;

import application.ApplicationExecution;
import database.Employee;
import database.EmployeeRole;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/home-page")
public class HomePage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            HttpSession session = request.getSession();
            Employee loggedEmployee = (Employee) session.getAttribute("loggedEmployee");
            EmployeeRole loggedEmployeeRole = (EmployeeRole) session.getAttribute("loggedEmployeeRole");

            if(loggedEmployeeRole!=null){
                List<String> uiButtons = ApplicationExecution.getRoleUIButtons(loggedEmployeeRole.getRoles().get(0));
                if(!uiButtons.isEmpty()){
                    request.setAttribute("buttons",uiButtons);
                    request.setAttribute("employee", loggedEmployee);
                    request.setAttribute("employeeRole", loggedEmployeeRole);
                }
            }
            request.getRequestDispatcher("/homepage.jsp").forward(request,response);
            /*response.setContentType("text/html");
            String message = "This is home page via get method for employee - "+ loggedEmployee.getEmpUserName();
            // Hello
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>" + message + "</h1>");
            out.println("</body></html>");*/
        }catch(Exception e){ }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String button = request.getParameter("buttonClicked");

            if(!button.isEmpty()) {
                switch (button) {
                    case "Configure Access":
                        response.sendRedirect("all-employee-access");
                        break;
                    case "Create Employee":
                        response.sendRedirect("create-employee");
                        break;
                    case "Create Request":
                        response.sendRedirect("create-request");
                        break;
                    case "Show Requests":
                        response.sendRedirect("show-all-request");
                    case "My Areas":
                        response.sendRedirect("my-area");
                }
            }
        }catch(Exception e){ }
    }
}
