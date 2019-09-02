package com.zby.emailCode;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@WebServlet("/MailServlet")
public class MailServlet extends HttpServlet {
    private MimeMessage createMimeMessage(Session session, String send, String receive, int numcode) throws Exception{
        // TODO Auto-generated method stub
        MimeMessage message = new MimeMessage(session);
        // 邮件：标题、正文、收件人 {附件、图片}
        Address address = new InternetAddress(send,"zby云","UTF-8");
        message.setFrom(address);
        message.setSubject("密码找回", "UTF-8");





        message.setContent("你好，你的验证码是:"+numcode+",悄悄的！！","text/html;charset=utf-8");

        //收件人类型：普通收件人（TO），抄送(CC)，密送(BCC)；
        message.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(receive,"收件人A","UTF-8"));
		/*
		message.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(cReceive,"抄送人B","UTF-8"));
		message.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(mReceive,"密送人C","UTF-8"));
		*/
        message.setSentDate(new Date());//设置发送时间

        message.saveChanges();//保存邮件

        return message;
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String useremail = request.getParameter("email");   //获取前台的邮箱
        System.out.println(useremail);




        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");// 使用的邮件协议
        props.setProperty("mail.smtp.host", "smtp.qq.com");// 协议地址
        props.setProperty("mail.smtp.port", "465");// 协议端口
        props.setProperty("mail.smtp.auth", "true");// 需要授权
        // QQ：SSL安全认证
        props.setProperty("mail.smtp.scoketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtp.ssl.enable", "true");
        Session session = Session.getInstance(props);
        session.setDebug(true);//开启日志
        // 创建邮件

        try {

            int numcode =(int)(Math.random()*8999)+1000;//生成随机验证码   传到MailServletCode中等待jsp返回验证
            MimeMessage message = createMimeMessage(session,"381016296@qq.com",useremail,numcode);
            Transport transport = session.getTransport();
            transport.connect("381016296@qq.com", "xpvhpxhcarfmbijc");//建立连接，其中密码以授权码方式填写
            transport.sendMessage(message, message.getAllRecipients());

            //String emailcode = String.valueOf(numcode);
            //System.out.println("int转String后：" + emailcode);
//            request.setAttribute("numcode", numcode);
//
//            request.setAttribute("useremail", useremail);
            HttpSession sessions = request.getSession();  //保存到session
            sessions.setAttribute("sessionnumcode",numcode);


            request.getRequestDispatcher("index.jsp?flag=email_ok?type=true").forward(request,response);
            transport.close();


        } catch (NoSuchProviderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//建立连接对象
        catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
