package com.example.enterpriseaccessrequestsystem;

import application.ApplicationExecution;
import database.Employee;
import database.Request;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/create-request")
public class CreateRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            ArrayList<String> allArea = new ArrayList<>(ApplicationExecution.getAllArea());

            request.setAttribute("allArea",allArea);
            request.getRequestDispatcher("/createrequest.jsp").forward(request,response);
        }catch(Exception e){}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Request req = new Request();
            Employee emp = (Employee) request.getSession().getAttribute("loggedEmployee");
            req.setAreaName(request.getParameter("area"));

            if(ApplicationExecution.createRequest(req,emp).getReqId()!=0){
                request.getSession().setAttribute("taskExecuted", (int)request.getSession().getAttribute("taskExecuted")+1);
                response.sendRedirect("show-request-detail");
            }

        }catch (Exception e){}
    }
}
