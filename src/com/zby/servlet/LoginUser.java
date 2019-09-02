package com.zby.servlet;

import com.zby.dbutil.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@WebServlet("/LoginUser")
public class LoginUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String[] params = {request.getParameter("username"),request.getParameter("password")};
            Connection connection = DBUtil.getDataSourceWithC3P0ByXml().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from zby1 where name = ? and password = ?",params);
            for(int i=0;i<params.length;i++){
                preparedStatement.setObject(i+1,params[i]);
            }
            if (preparedStatement.executeQuery().next()){
                request.getSession().setAttribute("username",request.getParameter("username"));
                request.getRequestDispatcher("index.jsp?flag=login_ok").forward(request,response);
            }else {
                request.getRequestDispatcher("index.jsp?flag=login_error").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
