<%@ page import="java.net.HttpCookie" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.zby.entity.Information" %>
<%@ page import="com.zby.entity.sql" %>
<%@ taglib uri="http://www.zby123.club"  prefix="zby"  %>
<%@ taglib prefix="zbytaglib" uri="http://www.zby123.club" %>
<%--
  Created by IntelliJ IDEA.
  User: 赵博雅
  Date: 2019/2/25
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>zby</title>
      <script type="text/javascript" src="js/jquery-3.3.1.js" ></script>
      <style type="text/css">
          h1{color:#9F5000;}
          h2{color:#B15BFF;}
          a{text-decoration:none;}
          #div1{margin:0 auto;border:1px solid #000;width:640px;height:450px;font-size: 20px}
      </style>
    <script>

      var error='<%=request.getParameter("flag")%>'
      if(error=='add_ok'){
          alert("添加成功！")
      }else if(error=='del_ok') {
          alert("删除成功！")
      }else if(error=='ok') {
          alert("转账成功！")
          window.location.href="#7";
      }else if(error=='error') {
          alert("转账失败！")
      }else if(error=='login_error') {
          alert("没这个人！！")
      }else if(error=='queryall') {
          window.location.href="#4";
      }else if(error=='que_vague') {
          window.location.href="#5";
      }else if(error=='card') {
          window.location.href="#6";
      }else if(error=='sql_ok') {
          window.location.href="#8";
      }else if(error=='login_ok') {
          window.location.href="#9";
      }else if(error=='email_ok') {
          alert("邮件发送成功，请查看！")
          window.location.href="#10";
      }

    </script>

      <script type="text/javascript" src="/js/jquery-3.3.1.js">

          $(document).ready(function(){//初始化函数、就绪函数、加载函数

              // $("body").keydown(function(down){             //按键监控   13 ---》回车键
              //     if(down.keyCode == 13) {
              //         alert("按压***");
              //     }
              // });


              $("#checkcodeId").blur(function(){          //jsp验证码判断

                  var $checkcode = $("#checkcodeId").val();

                  //校验   :文本框中输入的值 发送到服务端。
                  //服务端： 获取文本框输入的值 ，和真实验证码图片中的值对比 ，并返回验证结果
                  $.post(
                      "CheckCodeServlet",//服务端地址
                      "checkcode="+$checkcode ,
                      function(result){//图片地址（imgs/right.jpg   imgs/wrong.jpg）
                          //result:  imgs/right.jpg
                          var resultHtml =  $("<img src='"+result+"' height='15px' width='15px'   />") ;
                          $("#tip").html(resultHtml);
                      }
                  );
              });

              $("#buttoncheckcode").blur(function(){          //java代码验证码判断
                  var text=$.trim($("#checkcode").val());
                  $.post("VerificationServlet",{checkcode:text},function(data){  //ajax-->post刷新
                      data=parseInt($.trim(data));
                      if(data>0){
                          alert("验证码正确！");
                          reload();
                      }else{
                          $("#checkcode").val("");
                          alert("验证码错误！");
                          // 将验证码清空
                          reload();  //验证失败后需要更换验证码
                      }
                  });
              });

              $("#inputid").click(function(){          //Email验证码判断
                  var numcode =$.trim($("#numcode").val());
                  var password =$.trim($("#password").val());
                  var email =$.trim($("#emailid").val());

                  $.post("MailServletCode",
                      {numcode:numcode,
                          password:password,
                          email:email
                      },
                      function(data){  //ajax-->post刷新
                      data=parseInt($.trim(data));
                      if(data>0){
                          alert("用户:" + email +",的密码修改为：" + password);
                      }else{
                          alert("邮箱验证码错误！");
                      }
                  });
              });

              // 图灵ajax

              $("#tulingid").click(function(){
                  var json =$.trim($("#tuling").val());

                  $.post("tulingServlet",
                      {
                          json:json
                      },
                      function(result){  //ajax-->post刷新
                          $("#tulingout").html(result);
                      });
              });


          });//ready()


            function check() {
                var checkcode = $("#usernameid").val();
                $.ajax({
                    url:"Servlet_del_Session",
                    请求方式:"post",
                    data:"checkcode="+checkcode,
                    success:function(result){
                        if(result == "true"){
                            window.location.reload();
                            alert("退出，session已清除！");

                        }
                    }
                });
            }

            function check1() {
                var $usernameid = $("#usernameid").val();
                var reg = /^1\d{10}$/;
                if(!reg.test($usernameid)){
                    $("#nameTip").css("display","none");
                }
            }

          function reloadCheckImg()       //jsp验证码刷新
          {                                                 //类似于强制刷新
              $("img").attr("src", "img.jsp?t="+(new Date().getTime()));  //<img src="...">
              $("#checkcodeId").val("");
          }


          function reload(){               //java代码验证码刷新
              document.getElementById("image").src="ImageServlet?date="+new Date().getTime();
              $("#checkcode").val("");   // 将验证码清空
          }


          var emailcode = '<%=(Integer)request.getAttribute("numcode")%>';
          var type ='<%=request.getParameter("type")%>';

          if(type=='true'){
              alert("验证码发送成功，请查看！");
          }

      </script>


  </head>
  <body>
  hello zby
  <a href="Servlet">获取cookie---->查看</a>
  <br/>
  <br/>
  <div id="div1">
      跳转页面（目录）：
      <br/>
      <a href="#1">1.数据库--->添加</a><br/>
      <a href="#2">2.数据库--->删除</a><br/>
      <a href="#3">3.查询（普通连接查询）</a><br/>
      <a href="#4">4.查询所有（c3p0连接）</a><br/>
      <a href="#5">5.模糊查询（c3p0）</a><br/>
      <a href="#6">6.card信息查询（c3p0 + ThreadLocal）</a><br/>
      <a href="#7">7.转账操作（c3p0 + ThreadLocal）</a><br/>
      <a href="#8">8.数据库信息获取（DatabaseMetaData）</a><br/>
      <a href="#9">9.网页登录（Session验证登录是否无效）</a><br/><br/>
      <a >--------------------------以下内容登陆后使用--------------------------</a><br/><br/>
      <a >1.图片验证码处理1（使用jsp生成验证码，ajax刷新验证【post】）</a><br/>
      <a >2.图片验证码处理2（使用servlet生成验证码，ajax刷新验证【post】）</a><br/>
      <a >3.邮箱验证码发送（smtp.qq.com）</a><br/>
      <a >4.邮箱验证码验证（Ajax post session）</a><br/>


  </div>

  <br/>
  <br/>
  
  <h1>自定义标签（参考右边的图）</h1>
  <h2>循环（标签内容）</h2>
    <zby:mytag num="4" >赵博雅></zby:mytag><img src="标签处理类执行流程.png" align ="right">

  <h2>标签内容获取-->更改</h2>

    <zby:totag value="5">ZBY</zby:totag>

  <h1>添加</h1>
  <form id="1" action="user_add" method="post"/>
  <p>姓名：<input type="text" name="name" /></p>
  <p>年龄：<input type="number" name="age" /></p>
    <input type="submit" value="提交"/>
  </form>



  <h1>删除</h1>
  <form id="2" action="user_del" method="post"/>
  <p>姓名：<input type="text" name="name" /></p>
  <input type="submit" value="删除"/>
  </form>



  <h1>查询（普通连接查询）</h1>
  <form id="3" action="user_que" method="post"/>
  <p>姓名：<input type="text" name="name" /></p>
  <p>查询结果：<input type="text" value="<%=request.getAttribute("users")%>" /></p>
  <input type="submit" value="查询"/>
  </form>



  <h1>查询所有（c3p0连接）</h1>
  <form id="4" action="user_que_all" method="post"/>
  <input type="submit" value="开始查询"/>
  </form>
  <table border="0" width="200px" rules=rows font-size="20px">
    <tr>
      <th>姓名</th>
      <th>年龄</th>
    </tr>
    <%
      try {
        List<Object[]> users =(List<Object[]>) request.getAttribute("usersd");
        for (Object[] user: users) {
    %>
    <tr>
      <td><font size="4" color="DodgerBlue"><%=user[0]%></font>
      </td>
      <td><font size="4" color="DodgerBlue"><%=user[1]%></font>
      </td>
    </tr>
    <%
        }
      }catch (Exception e){
      }
    %>
  </table>



  <h1>模糊查询（c3p0）</h1>
  <form id="5" action="user_que_vague" method="post"/>
  <p>姓名：<input type="text" name="name" /></p>
  <input type="submit" value="开始查询"/>
  </form>
  <table border="0" width="200px" rules=rows font-size="20px">
      <tr>
          <th>姓名</th>
          <th>年龄</th>
      </tr>
      <%
          try {
              List<Map<String,Object>> user =(List<Map<String,Object>>)request.getAttribute("user_vague");
              for ( Map<String,Object> map :user) {
      %>
      <tr>
          <td><font size="4" color="DodgerBlue"><%=map.get("name")%></font>
          </td>
          <td><font size="4" color="DodgerBlue"><%=map.get("age")%></font>
          </td>
      </tr>
      <%
              }
          }catch (Exception e){
          }
      %>
  </table>



  <h1>card信息查询（c3p0 + ThreadLocal）</h1>
  <form id="6" action="Bank_Query" method="post"/>
  <p>卡号：<input type="text" name="id" /></p>
  <div>
      <%
          try {
              List<Information> information =(List<Information>)request.getAttribute("information");
              for ( Information  in :information) {
      %>
      <p> 卡号：<input type="text" readonly unselectable="on" value="<%= in.getId()%>" />
          姓名：<input type="text" readonly unselectable="on" value="<%= in.getName()%>" />
          余额：<input type="text" readonly unselectable="on" value="<%= in.getBalance()%>" />
      </p>
      <%
              }
          }catch (Exception e){

          }
      %>
  </div>
  <input type="submit" value="卡号查询"/>
  </form>



  <h1>转账操作（c3p0 + ThreadLocal）</h1>
  <form id="7" action="TransferAccountsMoney" method="post"/>
  <p>转出卡号：<input type="number" name="outid" id="outid"/></p>
  <p>转入卡号：<input type="number" name="inid" id="inid"/></p>
  <p>转账金额：<input type="number" name="money" /></p>
  <input type="submit" value="转账"/>
  </form>



  <h1>数据库信息获取（DatabaseMetaData）</h1>
  <form id="8" action="sql_version" method="post"/>
  <input type="submit" value="查询"/>
  <%
      try {
          List<sql> information =(List<sql>)request.getAttribute("sql");
          for ( sql  in :information) {
  %>
  <br/>
        数据库类型：<%=in.getDbname()%>
  <br/>
        数据库连接：<%=in.getUsername()%>
  <br/>
        version ：<%=in.getVersion()%>
  <br/>
  <%
          }
      }catch (Exception e){
      }
  %>
  </form>




  <h1>网页登录（Session验证登录是否无效）</h1>
  <form id="9" action="LoginUser" method="post" >
      <p>用户名：<input type="text" name="username" id="usernameid"/><p id="nameTip" color="red" style="display:none">用户名格式错误</p></p>
      <p>密  码：<input type="text" name="password" id="passwordid"/><p id="passTip"color="red" style="display:none">密码格式错误</p></p>
      <input type="submit" value="登录跳转" />
  </form>
  <zbytaglib:login>
      <p style="color:green">${sessionScope.username},已登录！</p>

          <input type = "submit"  value="退出登录" onclick = "check();"/>






      <h1>验证码处理（jsp生成验证码，Ajax刷新）</h1>

      <input type="text" name="checkcode" id="checkcodeId" size="4"  />
      <!-- 验证码-->
      <a href="javascript:reloadCheckImg();"> <img src="img.jsp"/></a>
      <span id="tip">  </span>


      <h1>验证码处理（java生成验证码，Ajax刷新）</h1>
      <input type="text" name="checkcode"  id="checkcode">
      <img  src="ImageServlet" alt="验证码" id="image" />
      <a href="javascript:reload();"><label>换一张</label></a><br>
      <button id="buttoncheckcode" type="submit">注册</button>


      <h1>邮件发送（smtp.qq.com）</h1>
      <form id="10" method="post" action="MailServlet">
          <p>输入待找回账号的邮箱</p>
          <input type="text" id = "email" name="email"/>
          <input  type="submit" value="发送验证码" />
      </form>




      <h1>邮件密码找回（Ajax post session）</h1>
      <form action="MailServletCode" method="post">
          邮箱：<input type="text" name="useremail" id="emailid"/><br>
          更改密码：<input type="text" id="password" name="password"/> <br>
          确认密码：<input type="text" id="passwordsid" name="passwords"/> <br>
          邮箱验证码：<input type="text" id="numcode" name="numcode"/> <br>
          <input id="inputid" type="submit" value="提交"/>

      </form>


      <h1>图灵机器人测试</h1>

      <form action="tulingServlet" method="post">
          您：<input type="text" id="tuling" name="tuling"/> <br>
          <input id="tulingid" type="button" value="发送"/>

      </form>

      <span id="tulingout"></span>
      <br/>
      <br/>
      <h1>以下技术由springmvc提供</h1>
      <br/>

      <a href="firstSpringMVC">My First SpringMVC Demo</a>


      <br/>
      <br/>
      <br/>
      <br/>








  </zbytaglib:login>

  <zby:login_out>
      <p style="color:red">用户未登录！</p>
      <p style="color:red">------------以下内容登录后可用！------------</p>

  </zby:login_out>


  <%--<form id="11" action="test_query" method="post">
      <p>用户名：<input type="text" name="username" /></p>
      <p>密  码：<input type="text" name="password" /></p>
      <input type="submit" value="登录跳转"/>

  </form>
  <zbytaglib:login>
      <p style="color:green">${sessionScope.username},已登录！</p>
      <form id="10" action="index.jsp?flag=login_out" method="post">
          <input type="submit"  value="退出登录"/>
      </form>
  </zbytaglib:login>

  <zby:login_out>
      <p style="color:red">用户未登录！</p>
  </zby:login_out>
  <%
      session.removeAttribute("username");
  %>--%>







  </body>
</html>
