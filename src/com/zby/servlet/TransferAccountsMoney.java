package com.zby.servlet;

import com.zby.entity.Account;
import com.zby.service.UserServiceIn;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/TransferAccountsMoney")
public class TransferAccountsMoney extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServiceIn userServiceIn = new UserServiceIn();
        Account account = new Account(request.getParameter("outid"),request.getParameter("inid"),request.getParameter("money"));
            try{
                userServiceIn.account(account);
                request.getRequestDispatcher("index.jsp?flag=ok").forward(request,response);
            }catch (Exception e){
                request.getRequestDispatcher("index.jsp?flag=error").forward(request,response);
            }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
