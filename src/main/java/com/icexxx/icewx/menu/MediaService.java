package com.icexxx.icewx.menu;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.icexxx.icewx.cont.IceWxUrl;
import com.icexxx.icewx.util.IceWxUtil;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

public class MediaService {
    private static final Log log = LogFactory.get();

    public static JSONObject upload(String mediaUrl, String localFile, Map<String, Object> paramMap) {
        Map<String, String> urlToken = IceWxUtil.urlToken();
        String menuToken = urlToken.get("token");
        String url = urlToken.get("url");
        String accessTokenUrl = url + "/wx/accessToken?token=" + menuToken;
        String accessToken = HttpUtil.get(accessTokenUrl);
        mediaUrl = mediaUrl.replace("ACCESS_TOKEN", accessToken);
        Map<String, Object> paramMapAll = new HashMap<String, Object>();
        localFile = localFile.trim().replace("\\", "/");
        paramMapAll.put("media", new File(localFile));
        if (paramMap != null && paramMap.size() > 0) {
            paramMapAll.putAll(paramMap);
        }
        String post = HttpUtil.post(mediaUrl, paramMapAll);
        JSONObject jsonObject = JSONUtil.parseObj(post);
        return jsonObject;
    }

    public static String uploadImage(String localFile) {
        String mediaUrl = IceWxUrl.UPLOADIMG;
        JSONObject JSONObject = upload(mediaUrl, localFile, null);
        if (JSONObject.containsKey("url")) {
            return JSONObject.getStr("url");
        } else {
            log.error(JSONObject.toString());
            return null;
        }
    }
}
