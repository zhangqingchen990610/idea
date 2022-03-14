/*
 * Copyright (c) 2006, 2022, webrx.cn All rights reserved.
 *
 */
package cn;

import cn.webrx.DbUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>Project: jdbc - StatementDemo
 * <p>Powered by webrx On 2022-03-01 15:40:39
 * <p>Created by IntelliJ IDEA
 *
 * @author webrx [webrx@126.com]
 * @version 1.0
 * @since 17
 */
public class StatementDemo {
    DbUtils du = new DbUtils();


    @Test @DisplayName("Statement 测试数据删除修改")
    public void s6(){
        //修改数据
        try{
            Statement st = du.getConn().createStatement();
            int i = st.executeUpdate("update st set sname='李五',sscore=68 where sid = 109");
            System.out.println(i);

            //删除
            int a = st.executeUpdate("delete from st where sname = 'lisi3'");
            System.out.println(a);
        }catch(Exception e){
            e.printStackTrace();
        }
        s4();
    }

    @Test @DisplayName("Statement 测试数据插入")
    public void s5(){
        try{
            Statement st = du.getConn().createStatement();
            st.addBatch("insert into st value(101,'lisi',30)");
            st.addBatch("insert into st value(102,'lisi2',31)");
            st.addBatch("insert into st value(103,'lisi3',32)");
            int[] ss = st.executeBatch();
            System.out.println(ss.length);
            for (int s : ss) {
                System.out.println(s);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Test @DisplayName("Statement 测试数据插入")
    public void s3(){
        int id = 23;
        String name = "张三";
        int score = 70;
        String sql = String.format("insert into st value(%d,'%s',%d)",id,name,score);
        System.out.println(sql);
        try{
            Statement st = du.getConn().createStatement();
            int rows = st.executeUpdate(sql);
            if(rows>0){
                System.out.println("数据插入成功.");
                System.out.println("------------------------------");
                s4();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test @DisplayName("Statement 数据查询")
    public void s4(){
        String sql = "select * from st order by sid desc";
        try {
            Statement st = du.getConn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.isBeforeFirst()){
                while(rs.next()){
                    System.out.println(rs.getInt(1) + ":"+rs.getString(2));
                }
            }else{
                System.out.println("查询没有数据结果。");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void s2(){
        DbUtils du = new DbUtils();
        System.out.println(du.dbs());
        System.out.println(du.version());
        System.out.println(du.tbs());
    }
    @Test
    public void s1() {
        DbUtils du = new DbUtils();
        //du.connect("jdbc:mysql://localhost:3305?user=root");
        //System.out.println(du.dbs());

        Connection conn = du.getConn();
        try {
            Statement st = conn.createStatement();
            //st.executeUpdate() insert udpate delete
            //st.execute(sql) create drop alter grant
            //st.executeQuery() select show 返回ResultSet
            //st.addBatch(sql);
            //st.executeBatch()
            //st.executeLargeUpdate()
            //st.clearBatch();
            //st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
