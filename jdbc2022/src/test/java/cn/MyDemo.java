/*
 * Copyright (c) 2006, 2022, webrx.cn All rights reserved.
 *
 */
package cn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * <p>Project: jdbc - MyDemo
 * <p>Powered by webrx On 2022-03-01 14:18:01
 * <p>Created by IntelliJ IDEA
 *
 * @author webrx [webrx@126.com]
 * @version 1.0
 * @since 17
 */
public class MyDemo {
    @Test @DisplayName("连接ubuntu mysql5.6.51")
    public void m5() {
        //01 docker pull mysql:5.6.51
        //02 docker run -itd -p 49153:3306 --name m5 -e MYSQL_ROOT_PASSWORD=root -e TZ=Asia/Shanghai mysql:5.6.51
        //03 docker run -it --rm mysql:5.6.51 mysql -h192.168.21.68 -uroot -p -P49153
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://192.168.21.68:49153?serverTimezone=PRC";
        String user = "root";
        String password = "root";
        try{
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url,user,password);
            //String version = conn.getMetaData().getDatabaseProductVersion();
            //System.out.println(version);
            Statement st =  conn.createStatement();
            ResultSet rs = st.executeQuery("select now()");
            if(rs.next()){
                System.out.println(rs.getDate(1)); //java LocalDate mysql date
                System.out.println(rs.getTime(1)); //java LocalTime mysql time
                System.out.println(rs.getTimestamp(1)); //java LocalDateTime mysql datetime
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
