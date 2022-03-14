/*
 * Copyright (c) 2006, 2022, webrx.cn All rights reserved.
 *
 */
package cn.webrx.servlet;

import cn.webrx.DbUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * <p>Project: jdbc2022 - Reg
 * <p>Powered by webrx On 2022-03-02 16:08:54
 * <p>Created by IntelliJ IDEA
 *
 * @author webrx [webrx@126.com]
 * @version 1.0
 * @since 17
 */
@WebServlet("/reg.do")
public class Reg extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String a = req.getParameter("account").trim();
        String p = req.getParameter("pwd").trim();
        String n = req.getParameter("tname").trim();
        String ip = req.getRemoteAddr();

        DbUtils du = new DbUtils();
        String msg = "恭喜，注册成功";
        try{
            Connection conn = du.getConn();
            String sql = "insert into t_member(account,pwd,truename,regip) value(?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,a);
            pst.setString(2,du.pwd(a,p));
            pst.setString(3,n);
            pst.setString(4,ip);
            int rows = pst.executeUpdate();
            if(rows>0){

            }else{
                msg = "注册失败，请检查账号密码(可能账号已经存在)";
            }
        }catch(Exception e){
            msg = "注册失败，请检查账号密码(可能账号已经存在)";
        }

        req.setAttribute("msg",msg);
        req.getRequestDispatcher("/info.jsp").forward(req,resp);

    }
}
