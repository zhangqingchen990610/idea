<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>系统登录</title>
</head>

<body>
<form action="check" method="post">
    <label>账号：<input type="text" name="account"></label><br>
    <label>密码：<input type="password" name="pwd"></label><br>
    <hr>
    <input type="submit" value="登录">
    <input type="reset" value="重置">
</form>
</body>
</html>
