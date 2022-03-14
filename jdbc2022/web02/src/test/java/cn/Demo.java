/*
 * Copyright (c) 2006, 2022, webrx.cn All rights reserved.
 *
 */
package cn;

import cn.webrx.DbUtils;

/**
 * <p>Project: jdbc2022 - Demo
 * <p>Powered by webrx On 2022-03-02 17:17:52
 * <p>Created by IntelliJ IDEA
 *
 * @author webrx [webrx@126.com]
 * @version 1.0
 * @since 17
 */
public class Demo {
    public static void main(String[] args) {
        DbUtils du = new DbUtils();
        System.out.println(du.pwd("aa","1"));


    }
}
