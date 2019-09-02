package com.zby.servlet;

import com.zby.dbutil.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/user_que_vague")
public class user_que_vague extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSourceWithC3P0ByXml());

        try {
        // String name = "'%"+request.getParameter("name")+"%'";
            String name = request.getParameter("name");
                    System.out.println(name);

            List<Map<String,Object>> user = queryRunner.query("select * from demo where name like ? ",new MapListHandler(),"%"+name+"%");
//            for ( Map<String,Object> map :user) {
//                System.out.println(map.get("name")+":"+map.get("age"));
//            }
            request.setAttribute("user_vague",user);
            request.getRequestDispatcher("index.jsp?flag=que_vague").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
