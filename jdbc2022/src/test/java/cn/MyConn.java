/*
 * Copyright (c) 2006, 2022, webrx.cn All rights reserved.
 *
 */
package cn;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * <p>Project: jdbc - MyConn
 * <p>Powered by webrx On 2022-03-01 14:44:29
 * <p>Created by IntelliJ IDEA
 *
 * @author webrx [webrx@126.com]
 * @version 1.0
 * @since 17
 */
public class MyConn {
    Connection conn;

    @BeforeEach
    public void init() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://192.168.21.68:49153/db?user=root&password=root&serverTimezone=PRC&useUnicode=true&characterEncoding=utf8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void close() {
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("insert sql")
    public void add() {
        try {
            Statement s = conn.createStatement();
            String name = "李强";
            double money = 120;
            String sql = String.format("insert into t_student value(null,'%s',%.1f)",name,money);
            System.out.println(sql);
            int i = s.executeUpdate(sql);
            if(i>0){
                System.out.println("数据插入成功...");
                System.out.println("-----------------------------");
                query();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void query(){
        try{
            ResultSet rs = conn.createStatement().executeQuery("select * from t_student order by id desc");
            while(rs.next()){
                System.out.printf("姓名=%s%n",rs.getString("name"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
