package com.zby.checkcode;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedImage bfi = new BufferedImage(80,25,BufferedImage.TYPE_INT_RGB);
        Graphics g = bfi.getGraphics();
        g.fillRect(0, 0, 80, 25);

        //验证码字符范围
        char[] ch = "0123456789".toCharArray();
        Random r = new Random();
        int index;
        StringBuffer sb = new StringBuffer(); //保存字符串
        for(int i=0; i<4; i++){
            index = r.nextInt(ch.length);
            g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
            Font font = new Font("宋体", 30, 20);
            g.setFont(font);
            g.drawString(ch[index]+"", (i*20)+2, 23);
            sb.append(ch[index]);
        }

        // 添加噪点
        int area = (int) (0.02 * 60 * 25);
        for(int i=0; i<area; ++i){
            int x = (int)(Math.random() * 60);
            int y = (int)(Math.random() * 25);
            bfi.setRGB(x, y, (int)(Math.random() * 255));
        }

        //设置验证码中的干扰线
        for (int i = 0; i < 2; i++) {
            //随机获取干扰线的起点和终点
            int xstart = (int)(Math.random() * 60);
            int ystart = (int)(Math.random() * 25);
            int xend = (int)(Math.random() * 60);
            int yend = (int)(Math.random() * 25);
            //g.setColor(interLine(1, 255));
            g.drawLine(xstart, ystart, xend, yend);
        }
        HttpSession session = request.getSession();  //保存到session
        session.setAttribute("verificationCode", sb.toString());
        ImageIO.write(bfi, "JPG", response.getOutputStream());  //写到输出流
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
