package com.icexxx.icewx.menu.button;

import com.icexxx.icewx.menu.type.Miniprogram;

/**
 * icewx &nbsp; 菜单小程序按钮
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class MiniprogramButton implements WxButton {
    private String name;
    private final Miniprogram type = Miniprogram.MINIPROGRAM;
    private String url;
    private String appid;
    private String pagepath;

    public MiniprogramButton() {

    }

    public MiniprogramButton(String name, String url, String appid, String pagepath) {
        this.name = name;
        this.url = url;
        this.appid = appid;
        this.pagepath = pagepath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }

    public Miniprogram getType() {
        return type;
    }

    @Override
    public String toString() {
        return "MiniprogramButton [name=" + name + ", type=" + type + ", url=" + url + ", appid=" + appid
                + ", pagepath=" + pagepath + "]";
    }
}
