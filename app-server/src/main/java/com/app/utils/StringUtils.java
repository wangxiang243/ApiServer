package com.app.utils;

/**
 * Created by wangxiang2 on 14-7-1.
 */
public class StringUtils {

    private static final String[] osArray = {"Android", "iPhone", "iPod", "iPad", "Windows Phone", "MQQBrowser"};

    public boolean verifyAndroidOS(String agent){
        return agent.contains("Android");
    }

    public static void main(String[] args) {
        String androidPath = "/usr/local/tomcat8/webapps/xyyapi/WEB-INF/lib/aopalliance-1.0.jar";
        String filename = androidPath.substring(androidPath.lastIndexOf("/")+1);
        System.out.println(filename);
    }
}
