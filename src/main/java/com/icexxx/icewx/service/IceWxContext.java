package com.icexxx.icewx.service;

/**
 * icewx &nbsp; 配置文件内容上下文
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class IceWxContext {
    private static String accessToken;
    private static String appID;
    private static String appsecret;
    private static String url;
    private static String token;
    private static String defaultMessage;
    private static String messageSave;

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        IceWxContext.accessToken = accessToken;
    }

    public static String getAppID() {
        return appID;
    }

    public static void setAppID(String appID) {
        IceWxContext.appID = appID;
    }

    public static String getAppsecret() {
        return appsecret;
    }

    public static void setAppsecret(String appsecret) {
        IceWxContext.appsecret = appsecret;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        IceWxContext.url = url;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        IceWxContext.token = token;
    }

    public static String getDefaultMessage() {
        return defaultMessage;
    }

    public static void setDefaultMessage(String defaultMessage) {
        IceWxContext.defaultMessage = defaultMessage;
    }

    public static String getMessageSave() {
        return messageSave;
    }

    public static void setMessageSave(String messageSave) {
        IceWxContext.messageSave = messageSave;
    }

}
