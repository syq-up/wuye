package com.shiyq.wuye.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shiyq
 * @create 2021-03-14 14:46
 */
public class ServletUtils {

    /**
     * 获取项目网络地址
     * http://ip:port/projectName
     * @param request
     * @return
     */
    public static String getProjectHttpUrl(HttpServletRequest request) {
        // 获取项目名称
        String contextPath = request.getContextPath();
        // 获取协议
        String scheme = request.getScheme();
        // 获取ip
        String serverName = request.getServerName();
        // 获取端口
        int serverPort = request.getServerPort();
        String showPath = scheme + "://" + serverName + ":" + serverPort + contextPath + "/";
        return showPath;
    }

}
