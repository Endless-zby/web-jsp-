package com.zby.servlet;

import com.zby.entity.Information;
import com.zby.service.UserServiceIn;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Bank_Query")
public class Bank_Query extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServiceIn userServiceIn = new UserServiceIn();
        String id = request.getParameter("id");
        Information information = new Information(null,id,null);
        try{
            List<Information> informations = userServiceIn.information(information);
            request.setAttribute("information",informations);
            request.getRequestDispatcher("index.jsp?flag=card").forward(request,response);
        }catch (Exception e){
            e.getLocalizedMessage();
            request.getRequestDispatcher("index.jsp?flag=login_error").forward(request,response);
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
