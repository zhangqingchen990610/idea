/*
 * Copyright (c) 2006, 2022, webrx.cn All rights reserved.
 *
 */
package cn;

import cn.webrx.DbUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * <p>Project: jdbc2022 - ResultSetDemo
 * <p>Powered by webrx On 2022-03-03 11:31:16
 * <p>Created by IntelliJ IDEA
 *
 * @author webrx [webrx@126.com]
 * @version 1.0
 * @since 17
 */
public class ResultSetDemo {
    DbUtils du = new DbUtils();

    @Test
    public void rs2() {
        Connection conn = du.getConn();
        String sql = "select id,account,truename from t_member";
        try {
            PreparedStatement pst = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pst.executeQuery();

            rs.last();
            System.out.println(rs.getString(3));

            rs.first();
            System.out.println(rs.getString(3));

            rs.next();
            System.out.println(rs.getString(3));

            //rs.absolute(1000);
            rs.absolute(3);
            rs.next();
            rs.previous();
            rs.previous();
            System.out.println(rs.getString(3));

            rs.beforeFirst();
            while(rs.next()){
                System.out.println(rs.getString(3));
            }
            rs.previous();
            rs.getString(3);


            rs.close();//关闭结果集对象
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void rs1() {
        Connection conn = du.getConn();
        String sql = "select id,account,truename from t_member where true order by id desc";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs.isBeforeFirst());//true
            System.out.println(rs.isAfterLast());//false
            System.out.println(rs.isFirst());//false
            System.out.println(rs.isLast());//false
            System.out.println(rs.isClosed());//false

            boolean f = false;
            while(rs.next()){
                System.out.println(rs.getString(3));
                f = true;
            }
            if(!f){
                System.out.println("没有数据");
            }

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    System.out.println(rs.getString(3));
                }
            }else{
                System.out.println(sql+"(查询没有数据)");
            }


            //System.out.println(rs.next());//true

            //System.out.println(rs.getString(3));


            rs.close();//关闭结果集对象
            System.out.println(rs.isClosed()); //true
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
