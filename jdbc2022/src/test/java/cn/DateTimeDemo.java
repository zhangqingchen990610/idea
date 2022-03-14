/*
 * Copyright (c) 2006, 2022, webrx.cn All rights reserved.
 *
 */
package cn;

import cn.webrx.DbUtils;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * <p>Project: jdbc2022 - DateTimeDemo
 * <p>Powered by webrx On 2022-03-03 14:23:52
 * <p>Created by IntelliJ IDEA
 *
 * @author webrx [webrx@126.com]
 * @version 1.0
 * @since 17
 */
public class DateTimeDemo {
    DbUtils du = new DbUtils();


    @Test
    public void d3() {
        Connection conn = du.getConn();
        String sql = "select id,name,userfile from t_user";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id"));
                System.out.println(rs.getString(2));

                //读取文件，字节，保存下来
                Blob file = rs.getBlob(3);
                String fn = "d:\\abc\\"+ UUID.randomUUID().toString() +".jpg";
                file.getBinaryStream().transferTo(new FileOutputStream(fn));
                System.out.println("----------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void d2() {
        Connection conn = du.getConn();
        String sql = "select id,name,addtime,birth,regtime,money from t_user order by id desc";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id"));
                System.out.println(rs.getString(2));
                LocalDateTime dt = rs.getTimestamp(3).toLocalDateTime();
                System.out.println(dt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                System.out.println(dt.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));
                System.out.println(dt.format(DateTimeFormatter.ofPattern("MM月dd日")));
                //System.out.println(rs.getTimestamp("addtime")); 2000-12-22 18:20:30.0
                LocalDate d = rs.getDate("birth").toLocalDate();
                System.out.println(d.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));
                LocalTime t = rs.getTime("regtime").toLocalTime();
                System.out.println(t);
                double dou = rs.getDouble("money");
                System.out.printf("金额：%.1f%n", dou);
                System.out.println("----------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void d1() {
        Connection conn = du.getConn();
        String sql = "insert into t_user value(null,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "王强");
            //java 1.8 LocalDateTime对象
            //pst.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            //pst.setTimestamp(2,Timestamp.valueOf("2008-08-08 15:30:40"));
            pst.setTimestamp(2, Timestamp.valueOf(LocalDateTime.parse("2000-12-22 18:20:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
            //java 1.8 LocalDate 对象
            //pst.setDate(3, Date.valueOf(LocalDate.now()));
            pst.setDate(3, Date.valueOf("1999-03-15"));
            pst.setTime(4, Time.valueOf("09:25:33"));
            //pst.setTime(4, Time.valueOf(LocalTime.now()));
            FileInputStream is = new FileInputStream("d:\\p.jpg");
            pst.setBlob(5, is);
            pst.setDouble(6, 100);

            int row = pst.executeUpdate();
            if (row > 0) {
                System.out.println("数据插入成功");
            } else {
                System.out.println("失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
