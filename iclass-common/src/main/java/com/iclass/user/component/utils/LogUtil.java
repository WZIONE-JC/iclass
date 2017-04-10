package com.iclass.user.component.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by radishmaster on 10/04/17.
 *
 * 日志记录工具类
 */
public class LogUtil {

    /**
     * 获取客户端ip
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

    /**
     * 获取客户端设备
     * @param request
     * @return
     */
    public static String getDevice(HttpServletRequest request) {
        String user_agent = request.getHeader("User-Agent");
        String device = user_agent.substring(0, user_agent.indexOf(")") + 1);
        return device;
    }

    /**
     * 获取客户端的操作
     * @param request
     * @return
     */
    public static String getOperation(HttpServletRequest request) {

        return request.getRequestURI();
    }

    /**
     * 获取客户端查询内容
     * @param request
     * @return
     */
    public static String getQueryString(HttpServletRequest request) {

        String queryString = request.getQueryString();
        return queryString == null ? "无内容" : queryString;
    }

    /**
     * 获取请求的方法
     * @param request
     * @return
     */
    public static String getMethod(HttpServletRequest request) {

        return request.getMethod();
    }
}
