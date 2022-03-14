/*
 * Copyright (c) 2006, 2022, webrx.cn All rights reserved.
 *
 */
package cn;

import cn.webrx.DbUtils;

import java.io.InputStream;

/**
 * <p>Project: jdbc - Demo
 * <p>Powered by webrx On 2022-03-01 17:35:27
 * <p>Created by IntelliJ IDEA
 *
 * @author webrx [webrx@126.com]
 * @version 1.0
 * @since 17
 */
public class Demo {
    public static void main(String[] args) {
        System.out.println("demo");
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
        System.out.println(is);
        DbUtils du = new DbUtils();
    }
}
