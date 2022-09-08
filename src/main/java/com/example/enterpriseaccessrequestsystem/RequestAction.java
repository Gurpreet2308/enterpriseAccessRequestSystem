package com.example.enterpriseaccessrequestsystem;

import application.ApplicationExecution;
import database.Request;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/request-action")
public class RequestAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Request req = new Request();
            req.setReqId(Long.valueOf(request.getParameter("reqId")));
            req.setEmpId(Long.valueOf(request.getParameter("empId")));
            req.setAreaRequested(Long.valueOf(request.getParameter("areaRequested")));
            String action = request.getParameter("buttonClicked");
            switch (action){
                case "Accept":
                    if(ApplicationExecution.modifyRequestStatus(req,"Accepted")){
                        request.getSession().setAttribute("taskExecuted", (int)request.getSession().getAttribute("taskExecuted")+1);
                        request.getRequestDispatcher("show-all-request").forward(request,response);
                    }
                    break;
                case "Reject":
                    if(ApplicationExecution.modifyRequestStatus(req,"Rejected")){
                        request.getSession().setAttribute("taskExecuted", (int)request.getSession().getAttribute("taskExecuted")+1);
                        request.getRequestDispatcher("show-all-request").forward(request,response);
                    }
                    break;
            }
        }catch(Exception e){}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
