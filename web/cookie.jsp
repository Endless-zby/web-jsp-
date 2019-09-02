<%--
  Created by IntelliJ IDEA.
  User: 赵博雅
  Date: 2019/2/25
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Cookie[] cookies = request.getCookies();
    for (Cookie c : cookies) {
        out.println(c.getName()+":"+c.getValue()+"------"+c.getPath());
    }
%>
</body>
</html>
