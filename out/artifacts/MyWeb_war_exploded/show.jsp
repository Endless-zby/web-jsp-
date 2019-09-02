<%@ taglib prefix="zbytaglib" uri="http://www.zby123.club" %>
<%@ page import="com.zby.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.zby.entity.queuser" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.zby.entity.Information" %>
<%@ page import="com.zby.entity.sql" %><%--
  Created by IntelliJ IDEA.
  User: 赵博雅
  Date: 2019/2/25
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="0" width="200px" rules=rows font-size="20px">
<%
    try {
        List<sql> information =(List<sql>)request.getAttribute("sql");
        for ( sql  in :information) {
            out.print(in.getDbname()+"---"+in.getUsername()+"---"+in.getVersion());
%>
    <tr>
        <td><font size="2" color="DodgerBlue"><%=in.getDbname()%></font>
        </td>
        <td><font size="2" color="DodgerBlue"><%=in.getUsername()%></font>
        </td>
        <td><font size="2" color="DodgerBlue"><%=in.getVersion()%></font>
        </td>
    </tr>
    <%
    }
        }catch (Exception e){

    }
    %>
</table>



<%
    session.removeAttribute("username");
%>
<zbytaglib:login>
    ${sessionScope.username},已登录
</zbytaglib:login>



</body>
</html>
