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
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * <p>Project: jdbc2022 - Check
 * <p>Powered by webrx On 2022-03-02 16:49:38
 * <p>Created by IntelliJ IDEA
 *
 * @author webrx [webrx@126.com]
 * @version 1.0
 * @since 17
 */
@WebServlet("/check")
public class Check extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String a = req.getParameter("account").trim();
        String p = req.getParameter("pwd").trim();

        DbUtils du = new DbUtils();
        String msg = "登录成功";
        try{
            Connection conn = du.getConn();
            String sql = "select count(*) from t_member where account=? and pwd=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,a);
            pst.setString(2,du.pwd(a,p));

            //Statement pst = conn.createStatement();
            //ResultSet rs = pst.executeQuery("select count(*) from t_member where account='"+a+"' and pwd='"+p+"'");

            //System.out.println("select count(*) from t_member where account='"+a+"' and pwd='"+p+"'");

            ResultSet rs = pst.executeQuery();
            rs.next();
            int ok = rs.getInt(1);
            if(ok>0){

            }else{
                msg = "登录失败";
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        req.setAttribute("msg",msg);
        req.getRequestDispatcher("info.jsp").forward(req,resp);
    }
}
