<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>会员系统</title>
</head>

<body>
<h3>首页</h3>
<a href="login.jsp">登录</a>
<a href="reg.jsp">注册</a>
<hr>
<%
    //JSP java server pages
    //Class.forName("com.mysql.cj.jdbc");
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root");
    out.print("<h3>"+ conn.getMetaData().getDatabaseProductVersion() +"</h3>");

    PreparedStatement pst = conn.prepareStatement("show databases");
    ResultSet rs = pst.executeQuery();
    while(rs.next()){
        String db = rs.getString(1);
        if("mysql".equals(db) || "information_schema".equals(db) || "sys".equals(db) || "performance_schema".equals(db)){
            continue;
        }
        out.print(String.format("<p>%s</p>",db));
    }

%>
</body>
</html>
