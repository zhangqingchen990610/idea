package cn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class Demo1 {
    @Test @DisplayName("测试连接库mysql 5.6.51")
    public void m5(){
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://192.168.21.76:3306/db";
        String uid = "root";
        String pwd = "123";
        try {
            //01 加载驱动，也可以写，jdbc会自动加载
            Class.forName("com.mysql.cj.jdbc.Driver");
            //02 建立连接
            //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/","root","");
            Connection conn = DriverManager.getConnection("jdbc:mysql://:3305/db","root","");
            //Connection conn = DriverManager.getConnection("jdbc:mysql://:3305/mysql","root","");
            //Connection conn = DriverManager.getConnection("jdbc:mysql://:3305/mysql?useUnicode=true&charactorEncoding=utf8&useSSL=false","root","");

            //03 执行sql语句
            Statement st = conn.createStatement();
            //st.execute("create database db default charset utf8");
            //st.execute("create table stu(id int unsigned auto_increment,name varchar(30),primary key(id))");

            //int rows = st.executeUpdate("insert into stu values(null,'李四'),(null,'Jack'),(null,'张三丰')");
            //System.out.println(rows);

            ResultSet rs = st.executeQuery("select * from stu order by id desc");
            if(rs.isBeforeFirst()){
                while(rs.next()){
                    System.out.printf("id:%d,name:%s. %n",rs.getInt(1),rs.getString("name"));
                }
            }else{
                System.out.println("没有数据");
            }

            //04 关闭数据库连接
            st.close();
            rs.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test @DisplayName("测试mysql1234 8.0.28 数据连接操作")
    public void m8(){
        String driver = "";
        String url = "jdbc:mysql://localhost:3306/db?useUnicode=true&characterEncoding=utf8&serverTimezone=PRC";
        String uid = "root";
        String pwd = "123";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql:/db","root","");
            //System.out.println(conn.getMetaData().getDatabaseProductVersion());
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("select * from student");
            while(rs.next()){
                System.out.println(rs.getString("sname"));
            }
            rs.close();
            s.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
