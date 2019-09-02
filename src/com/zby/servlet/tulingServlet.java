package com.zby.servlet;

import com.zby.tuling.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet("/tulingServlet")
public class tulingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Util utils = new Util();

        String json = request.getParameter("json");
        System.out.println("--------" + json);
        request.setCharacterEncoding("utf-8");  //这里不设置编码会有乱码
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        String outs = utils.getMessage(json);
        System.out.println(outs);
        out.println(outs);
        out.flush();
        out.close();

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.entrySet();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
