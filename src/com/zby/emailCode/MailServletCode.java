package com.zby.emailCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/MailServletCode")
public class MailServletCode extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        String servercode = String.valueOf((int) session.getAttribute("sessionnumcode"));

        String numcode = request.getParameter("numcode");

        PrintWriter out = response.getWriter();
        if(servercode.equals(numcode)){
            out.println(1);

        }else{
            out.println(0);
        }
        out.flush();
        out.close();



//        // System.out.println("修改后的密码为："+password);
//        response.setContentType("text/html;charset=utf-8");
//        PrintWriter out = response.getWriter();
//        out.println("<html><head><title>ConcurrentServlet</title></head><body><h1>");
//        out.println("用户" + email + "修改后的密码为：" + password);
//        out.println("</h1></body></html>");
//        out.close();
    }
}
