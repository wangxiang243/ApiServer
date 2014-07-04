package com.app.controller;

import com.app.common.Constants;
import com.app.utils.FileUtils;
import com.sun.net.httpserver.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;

/**
 * Created by wangxiang2 on 14-7-1.
 */
@RequestMapping("/app")
@Controller
public class DownloadController {

    @Value("${app.androidPath}")
    private String androidPath;

    @Value("${app.iphonePath}")
    private String iphonePath;

    public static final Logger logger = LoggerFactory.getLogger(DownloadController.class);

    @RequestMapping("download")
    public void download(HttpServletRequest request, HttpServletResponse response) {
        logger.info("androidPath=" + androidPath);
        logger.info("iphonePath=" + iphonePath);
        ByteArrayOutputStream out = null;
        OutputStream responseOut = null;
        try {
            responseOut = response.getOutputStream();
            Enumeration e = request.getHeaderNames();
            while (e.hasMoreElements()) {
                logger.info(e.nextElement().toString());
            }
            String userAgent = request.getHeader("User-Agent");
            logger.info("User-Agent=" + userAgent);
            logger.info("user-agent=" + request.getHeader("user-agent"));
            String filename = null;
            if (userAgent.contains(Constants.ANDROIDOS)) {
                out = FileUtils.getOutputStream(androidPath);
                filename = androidPath.substring(androidPath.lastIndexOf("/")+1);
            } else if (userAgent.contains(Constants.IPHONEOS)) {
                out = FileUtils.getOutputStream(iphonePath);
                filename = iphonePath.substring(androidPath.lastIndexOf("/")+1);
            } else {
                logger.error("暂不支持该设备:" + userAgent);
                return;
            }
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.setContentType("application/octet-stream");
            responseOut.write(out.toByteArray());
            responseOut.flush();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("下载安装包异常:"+e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (responseOut != null) {
                try {
                    responseOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
