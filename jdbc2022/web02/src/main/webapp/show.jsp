<%@ page import="cn.webrx.DbUtils" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>玉灵 QQ:7031633 Email:webrx@126.com</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<body>
<div class="container">
    <table class="table table-bordered table-striped table-hover" style="margin-top:25px">
        <thead>
        <tr>
            <td>账号</td>
            <td>姓名</td>
            <td>注册时间</td>
            <td>基本操作</td>
        </tr>
        </thead>
        <tbody>
        <%
            DbUtils du = new DbUtils();
            Connection conn = du.getConn();
            PreparedStatement pst = conn.prepareStatement("select * from t_member order by id desc");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
        %>
        <tr>
            <td><%=rs.getString("account")%></td>
            <td><%=rs.getString("truename")%></td>
            <td><%=rs.getTimestamp("regtime")%></td>
            <td><button class="btn btn-sm btn-danger">删除</button></td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
