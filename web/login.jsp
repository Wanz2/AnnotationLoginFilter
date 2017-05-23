<%--
  Created by IntelliJ IDEA.
  User: wuwenan
  Date: 10/05/2017
  Time: 11:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<form action="/servlet/AnnotationLoginServlet" method="post">
    用户名:<input type="text" name="username">
    密码：<input type="password" name="password">
    <input type="submit" name="submint" value="登录">
</form>
</body>
</html>
