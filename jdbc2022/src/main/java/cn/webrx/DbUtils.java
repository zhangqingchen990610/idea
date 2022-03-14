/*
 * Copyright (c) 2006, 2022, webrx.cn All rights reserved.
 *
 */
package cn.webrx;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * <p>Project: jdbc - DbUtils
 * <p>Powered by webrx On 2022-03-01 15:41:22
 * <p>Created by IntelliJ IDEA
 *
 * @author webrx [webrx@126.com]
 * @version 1.0
 * @since 17
 */
public class DbUtils {
    private String driver;
    private String url;
    private String user;
    private String password;
    private Connection conn;

    public DbUtils(){
        init();
    }

    public List<String> dbs(){
        List<String> list = new ArrayList<>();
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("show databases");
            while(rs.next()){
                list.add(rs.getString(1));
            }
            rs.close();
            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public void close(){
        try{
            if(conn!=null){
                conn.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void connect(String url,String user,String password){
        try{
            conn = DriverManager.getConnection(url,user,password);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void connect(String url){
        try{
            conn = DriverManager.getConnection(url);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void init(){
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
        Properties prop = new Properties();
        try {
            prop.load(is);
            driver = prop.getProperty("db.driver");
            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String version(){
        String ver = "";
        try{
            ver = conn.getMetaData().getDatabaseProductVersion();
        }catch(Exception e){
            e.printStackTrace();
        }
        return  ver;
    }

    public int count(String sql,Object...params){
        int num = 0;
        try{
            PreparedStatement p = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                p.setObject(i+1,params[i]);
            }
            ResultSet rs = p.executeQuery();
            rs.next();
            num = rs.getInt(1);
        }catch(Exception e){
            e.printStackTrace();
        }
        return num;
    }

    public List<String> tbs(){
        List<String> list = new ArrayList<>();
        try{
            ResultSet rs = conn.createStatement().executeQuery("show tables");
            while(rs.next()){
                list.add(rs.getString(1));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return  list;
    }

    public List<String> tbs(String dbname){
        List<String> list = new ArrayList<>();
        try{
            ResultSet rs = conn.createStatement().executeQuery("show tables from "+dbname);
            while(rs.next()){
                list.add(rs.getString(1));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return  list;
    }


    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
