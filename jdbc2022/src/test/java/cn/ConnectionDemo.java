/*
 * Copyright (c) 2006, 2022, webrx.cn All rights reserved.
 *
 */
package cn;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * <p>Project: jdbc - ConnectionDemo
 * <p>Powered by webrx On 2022-03-01 15:16:49
 * <p>Created by IntelliJ IDEA
 *
 * @author webrx [webrx@126.com]
 * @version 1.0
 * @since 17
 */
public class ConnectionDemo {

    @Test
    public void c1() {
        try{
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql:/db?user=root");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void c2() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/db";
        String uid = "root";
        String pwd = "";
        try{
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url,uid,pwd);
            System.out.println(conn);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void c3() {
        String url = "jdbc:mysql://localhost:3306/db";
        Properties prop = new Properties();
        prop.setProperty("user","root");
        prop.setProperty("password","");
        prop.setProperty("serverTimezone","PRC");
        try{
            Connection conn = DriverManager.getConnection(url,prop);
            System.out.println(conn);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void c4() {
        //加载src/resources/db.properties 文件 01
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
        //InputStream is = ConnectionDemo.class.getClassLoader().getResourceAsStream("db.properties");
        Properties prop = new Properties();
        try {
            prop.load(is);
            String driver = prop.getProperty("db.driver");
            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");
            System.out.println(driver+url+user+password);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //加载src/resources/db.properties 文件 02
        ResourceBundle res = ResourceBundle.getBundle("db");
        System.out.println(res.getString("db.driver"));
        System.out.println(res.getString("db.url"));
        System.out.println(res.getString("db.user"));
        System.out.println(res.getString("db.password"));
    }
}
