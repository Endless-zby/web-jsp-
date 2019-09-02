package com.zby.servlet;

import com.zby.entity.queuser;
import com.zby.service.UserService;
import com.zby.service.UserServiceIn;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user_que")
public class user_que extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        UserService userService = new UserServiceIn();
        queuser queusers = new queuser(name);
        List<queuser> usersd = userService.queryUsers(queusers);
        if (usersd.size()>0){
            request.setAttribute("users",usersd.get(0).getName());
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }else {
            request.setAttribute("users","不存在！");
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
