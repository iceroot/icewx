package com.icexxx.icewx.service;

import com.icexxx.icewx.cont.IceWxUrl;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * icewx &nbsp; AccessToken刷新
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class AccessTokenService {
    public static String refresh() {
        String url = IceWxUrl.ACCESS_TOKEN;
        String appID = IceWxContext.getAppID();
        String appsecret = IceWxContext.getAppsecret();
        url = url.replace("APPID", appID);
        url = url.replace("APPSECRET", appsecret);
        String json = HttpUtil.get(url);
        JSONObject parseObj = JSONUtil.parseObj(json);
        String accessToken = parseObj.getStr("access_token");
        IceWxContext.setAccessToken(accessToken);
        return url;
    }
}
