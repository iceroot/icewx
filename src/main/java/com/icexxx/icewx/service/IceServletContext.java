package com.icexxx.icewx.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * icewx &nbsp; servlet相关对象上下文包装器
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class IceServletContext {
    private static HttpServletRequest request;
    private static HttpServletResponse response;

    public static HttpServletRequest getRequest() {
        return request;
    }

    public static void setRequest(HttpServletRequest request) {
        IceServletContext.request = request;
    }

    public static HttpServletResponse getResponse() {
        return response;
    }

    public static void setResponse(HttpServletResponse response) {
        IceServletContext.response = response;
    }

}
