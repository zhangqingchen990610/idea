/*
 * Copyright (c) 2006, 2022, webrx.cn All rights reserved.
 *
 */
package cn;

import cn.webrx.DbUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

/**
 * <p>Project: jdbc2022 - PreparedStatementDemo
 * <p>Powered by webrx On 2022-03-02 09:36:29
 * <p>Created by IntelliJ IDEA
 *
 * @author webrx [webrx@126.com]
 * @version 1.0
 * @since 17
 */
public class PreparedStatementDemo {
    DbUtils du = new DbUtils();

    @Test
    @DisplayName("查询")
    public void p1() {
        Connection conn = du.getConn();
        String sql = "select sid,sname,sscore from st";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.printf("id:%d,name:%s,score:%d%n", rs.getInt(1), rs.getString("sname"), rs.getInt(3));
            }

            ResultSet ss = pst.executeQuery("show tables");
            while (ss.next()) {
                System.out.println(ss.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("参数查询")
    public void p2() {
        Connection conn = du.getConn();
        String sql = "select sid,sname,sscore from st where sid>? and sname like ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, 100);
            pst.setString(2, "李%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.printf("id:%d,name:%s,score:%d%n", rs.getInt(1), rs.getString("sname"), rs.getInt(3));
            }

            System.out.println("===============================");
            pst.setInt(1, 100);
            pst.setString(2, "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.printf("id:%d,name:%s,score:%d%n", rs.getInt(1), rs.getString("sname"), rs.getInt(3));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("数据插入")
    public void p3() {
        Connection conn = du.getConn();
        String sql = "insert into st value(?,?,?)";
        String sql2 = "insert into st set sscore=?,sid=?,sname=?";
        try {
            PreparedStatement p1 = conn.prepareStatement(sql);
            p1.setInt(1, 200);
            p1.setString(2, "张三丰");
            p1.setInt(3, 60);
            int a1 = p1.executeUpdate();
            System.out.println(a1);

            PreparedStatement p2 = conn.prepareStatement(sql2);
            p2.setInt(1, 80);
            p2.setInt(2, 201);
            p2.setString(3, "周强");
            int a2 = p2.executeUpdate();
            System.out.println(a2);

            p1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("数据删除")
    public void p4() {
        Connection conn = du.getConn();
        String sql = "delete from st where sid = ?";
        try {
            PreparedStatement p = conn.prepareStatement(sql);
            p.setInt(1, 102);
            int a = p.executeUpdate();
            System.out.println(a);

            p1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("修改数据")
    public void p5() {
        //id:201,name:周强,score:80
        Connection conn = du.getConn();
        String sql = "update st set sscore=? where sid=?";
        try {
            PreparedStatement p = conn.prepareStatement(sql);
            p.setInt(1, 99);
            p.setInt(2, 201);
            int a = p.executeUpdate();
            System.out.println(a);

            p1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    @DisplayName("插入数据，并获取自增id值的主键")
    public void p6() {
        //PreparedStatement
        Connection conn = du.getConn();
        String sql = "insert into t_stu value(?,?)";
        try {
            PreparedStatement p = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            p.setInt(1, 90);
            p.setString(2, "Andy");
            int a = p.executeUpdate();
            System.out.println(a);

            System.out.println("------------");
            ResultSet rs = p.getGeneratedKeys();
            rs.next();
            System.out.println(rs.getInt(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("插入数据，并获取自增id值的主键")
    public void p7() {
        //Statement
        Connection conn = du.getConn();
        try {
            Statement p = conn.createStatement();
            int a = p.executeUpdate("insert into t_stu value(null,'wangwu')", Statement.RETURN_GENERATED_KEYS);
            System.out.println(a);

            System.out.println("------------");
            ResultSet rs = p.getGeneratedKeys();
            rs.next();
            System.out.println(rs.getInt(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("查询数据，并实现分页显示")
    public void p8() {
        //System.out.println(du.count("select count(*) from t_stu where name like ?","李%"));

        //总记录
        int recordcount = du.count("select count(*) from t_stu");
        //每页多少条记录
        int pagesize = 3;
        //第几页
        int currpage = 2;
        //总页数
        int pagecount = recordcount % pagesize == 0 ? recordcount / pagesize : recordcount / pagesize + 1;
        int pc = (int) Math.ceil(1.0 * recordcount / pagesize);

       if(currpage<1) currpage=1;
       if(currpage>pagecount) currpage=pagecount;

       String sql = "select * from t_stu limit ?,?";
       try{
           PreparedStatement p = du.getConn().prepareStatement(sql);
           p.setInt(1,currpage*pagesize-pagesize);
           p.setInt(2,pagesize);
           ResultSet rs = p.executeQuery();
           while(rs.next()){
               System.out.println(rs.getInt(1)+rs.getString(2));
           }
           String pinfo = String.format("第%d页/共%d页，每页%d条/共%d条",currpage,pagecount,pagesize,recordcount);
           System.out.println(pinfo);
       }catch(Exception e){
           e.printStackTrace();
       }


    }

}
