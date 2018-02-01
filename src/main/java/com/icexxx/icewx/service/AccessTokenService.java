package com.icexxx.icewx.service;

import com.icexxx.icewx.cont.IceWxUrl;

import cn.hutool.core.date.DateTime;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

/**
 * icewx &nbsp; AccessToken刷新
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class AccessTokenService {
    private static final Log log = LogFactory.get();

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
        String time = DateTime.now().toString();
        log.info(time + ">>" + accessToken);
        return url;
    }
}
