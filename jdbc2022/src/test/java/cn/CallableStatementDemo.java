/*
 * Copyright (c) 2006, 2022, webrx.cn All rights reserved.
 *
 */
package cn;

import cn.webrx.DbUtils;
import org.junit.jupiter.api.Test;

import java.sql.*;

/**
 * <p>Project: jdbc2022 - CallableStatementDemo
 * <p>Powered by webrx On 2022-03-03 09:43:00
 * <p>Created by IntelliJ IDEA
 *
 * @author webrx [webrx@126.com]
 * @version 1.0
 * @since 17
 */
public class CallableStatementDemo {
    DbUtils du = new DbUtils();
    @Test
    public void c3(){
        Connection conn = du.getConn();
        try{
            CallableStatement cst = conn.prepareCall("{call pcc(?,?)}");
            cst.setString(1,"李%");
            cst.registerOutParameter(2, Types.INTEGER);
            cst.execute();
            System.out.println(cst.getInt(2));
            cst.close();

            String sql = "select count(*) from t_member where truename like ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,"李%");
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                System.out.println(rs.getInt(1));
            }
            rs.close();
            pst.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void c2(){
        Connection conn = du.getConn();
        try{
            CallableStatement cst = conn.prepareCall("{call pdel(?)}");
            cst.setLong(1,171726875188618102L);
            int rows = cst.executeUpdate();
            System.out.println(rows);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void c1(){
       Connection conn = du.getConn();
       try{
           //CallableStatement cst = conn.prepareCall("call pdbs");
           CallableStatement cst = conn.prepareCall("{call pdbs()}");
           ResultSet rs = cst.executeQuery();
           while(rs.next()){
               System.out.println(rs.getString(1));
           }
       }catch(Exception e){
           e.printStackTrace();
       }
    }
}
