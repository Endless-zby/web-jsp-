package com.zby.servlet;

import com.zby.entity.Login;
import com.zby.service.UserService;
import com.zby.service.UserServiceIn;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test_query")
public class test_query extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userServiceIn = new UserServiceIn();


        Login login = new Login(request.getParameter("username"),request.getParameter("password"));
        try {
            if (userServiceIn.login(login)){
                request.getSession().setAttribute("username",request.getParameter("username"));
                request.setAttribute("username11",request.getParameter("username"));
                request.getRequestDispatcher("index.jsp").forward(request,response);
            }else {
                request.getRequestDispatcher("error.jsp").forward(request,response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
