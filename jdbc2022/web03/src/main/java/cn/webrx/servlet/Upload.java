/*
 * Copyright (c) 2006, 2022, webrx.cn All rights reserved.
 *
 */
package cn.webrx.servlet;

import cn.webrx.DbUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>Project: jdbc2022 - Upload
 * <p>Powered by webrx On 2022-03-03 16:01:40
 * <p>Created by IntelliJ IDEA
 *
 * @author webrx [webrx@126.com]
 * @version 1.0
 * @since 17
 */

@WebServlet("/upload") @MultipartConfig
public class Upload extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Part photo = req.getPart("photo");
        String name = req.getParameter("user");
        String fn = photo.getSubmittedFileName();
        String msg = "";
        if("".equals(name.trim()) || photo.getSize()==0 || !fn.endsWith(".jpg")){
            msg = "文件上传失败(上传人必须填写，必须是jpg图像文件，不能是0字节文件)";
            req.setAttribute("msg",msg);
        }else{
            String path = req.getServletContext().getRealPath("upload");
            File p = new File(path);
            if(!p.exists()){
                p.mkdirs();
            }

            String filename = UUID.randomUUID().toString().toLowerCase();
            //将文件上传到了服务器上
            photo.write(p.getAbsolutePath()+"/"+ filename+".jpg");

            DbUtils du = new DbUtils();
            String sql = "insert into t_student(name,ufile,utime,uip) value(?,?,?,inet_aton(?))";
            try{
                PreparedStatement pst = du.getConn().prepareStatement(sql);
                pst.setString(1,name);
                pst.setString(2,filename);
                pst.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                pst.setString(4,req.getRemoteAddr());
                int rows = pst.executeUpdate();
                if(rows>0){
                    msg = String.format("上传成功(上传人：%s，文件名：%s，文件大小：%d字节。)",name,fn,photo.getSize());
                }
                du.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        req.getRequestDispatcher("info.jsp").forward(req,resp);
    }
}
