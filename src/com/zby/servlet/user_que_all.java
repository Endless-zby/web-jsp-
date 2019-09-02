package com.zby.servlet;

import com.zby.dbutil.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/user_que_all")
public class user_que_all extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSourceWithC3P0ByXml());
        try {
            List<Object[]> user = queryRunner.query("select * from demo",new ArrayListHandler());
            request.setAttribute("usersd",user);
            request.getRequestDispatcher("index.jsp?flag=queryall").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
