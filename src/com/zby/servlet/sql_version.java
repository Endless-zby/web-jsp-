package com.zby.servlet;

import com.zby.entity.sql;
import com.zby.service.UserServiceIn;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/sql_version")
public class sql_version extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServiceIn userServiceIn = new UserServiceIn();
        try {
            System.out.println(111);
            List<sql> sqls = userServiceIn.sql();
            System.out.println(222);
            for (sql sql: sqls) {
                System.out.println(sql.getDbname() +"+"+ sql.getUsername() +"+"+ sql.getVersion());
            }
            request.setAttribute("sql",sqls);
            request.getRequestDispatcher("index.jsp?flag=sql_ok").forward(request,response);

        }catch (Exception e){
            e.getLocalizedMessage();
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
