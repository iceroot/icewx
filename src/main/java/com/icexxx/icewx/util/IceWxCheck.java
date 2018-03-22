package com.icexxx.icewx.util;

import java.util.Arrays;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.setting.Setting;

public class IceWxCheck {

    public static boolean check() {
        Setting setting = new Setting("wx.properties");
        String url = setting.getStr("url");
        String token = setting.getStr("token");
        String oldUrl = url;
        if (url == null) {
            throw new RuntimeException("wx.properties文件url没有配置");
        } else {
            url = StrUtil.removePrefix(url, "http://");
            url = StrUtil.removePrefix(url, "https://");
            url = StrUtil.removeSuffix(url, "/");
            String address = url;
            String uri = null;
            if (url.contains("/")) {
                address = StrUtil.subBefore(url, "/", false);
                uri = StrUtil.subAfter(url, "/", false);
            }
            if (address.contains(":")) {
                String port = StrUtil.subAfter(address, ":", false);
                if (NumberUtil.isInteger(port)) {
                    int portNum = Integer.parseInt(port);
                    if (portNum != 80 && portNum != 443) {
                        throw new RuntimeException("wx.properties文件url目前仅支持80和443端口");
                    }
                }
            }
            if (uri != null) {
                if (uri.contains("/")) {
                    throw new RuntimeException("wx.properties文件url应该使用项目首页");
                }
            }
            StringBuilder urlString = new StringBuilder(oldUrl);
            String timestamp = DateUtil.currentSeconds() + "";
            String nonce = RandomUtil.randomNumbers(10);
            String echostr = RandomUtil.randomNumbers(19);
            String[] array = new String[] { token, timestamp, nonce };
            Arrays.sort(array);
            String signature = SecureUtil.sha1(ArrayUtil.join(array, ""));
            urlString.append("?signature=");
            urlString.append(signature);
            urlString.append("&timestamp=");
            urlString.append(timestamp);
            urlString.append("&nonce=");
            urlString.append(nonce);
            urlString.append("&echostr=");
            urlString.append(echostr);
            try {
                String result = HttpUtil.get(urlString.toString());
                if (StrUtil.isBlank(result)) {
                    throw new RuntimeException("url返回的内容为空");
                }
                if (result.contains("HTTP Status 404 -")) {
                    throw new RuntimeException("发生404错误,网页无法找到,url=" + oldUrl);
                }
                if (!result.equals(echostr)) {
                    throw new RuntimeException("签名不一致,请检查wx.properties文件");
                }
            } catch (cn.hutool.http.HttpException e) {
                if ("Connection refused: connect".equals(e.getMessage().trim())) {
                    throw new RuntimeException("[无法连接]: " + oldUrl);
                }
            }
        }
        return true;
    }
}
