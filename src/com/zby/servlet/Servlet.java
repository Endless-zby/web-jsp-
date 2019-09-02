package com.zby.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebServlet(name = "Servlet",urlPatterns="/Servlet")
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        request.getRequestDispatcher("cookie.jsp").forward(request,response);

        //response.sendRedirect("cookie.jsp");

       /* PrintWriter writer = response.getWriter();
        writer.print("<html>\n" +
                "<head>\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>");


        writer.print("zby");


        writer.print("</body>\n" +
                "</html>");*/

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
