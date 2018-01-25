package com.icexxx.icewx.menu.button;

import com.icexxx.icewx.menu.type.View;

/**
 * icewx &nbsp; 菜单跳转网址按钮
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class ViewButton implements WxButton {
    private String name;
    private final View type = View.VIEW;
    private String url;

    public ViewButton() {

    }

    public ViewButton(String name, String url) {
        this.name = name;
        this.url = url;
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

    public View getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ViewButton [name=" + name + ", type=" + type + ", url=" + url + "]";
    }
}
