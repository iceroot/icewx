package com.icexxx.icewx.menu.button;

import com.icexxx.icewx.menu.type.ViewLimited;

/**
 * icewx &nbsp; 菜单图文消息按钮
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class ViewLimitedButton implements WxButton {
    private String name;
    private final ViewLimited type = ViewLimited.VIEW_LIMITED;
    private String media_id;

    public ViewLimitedButton() {

    }

    public ViewLimitedButton(String name, String media_id) {
        this.name = name;
        this.media_id = media_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public ViewLimited getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ViewLimitedButton [name=" + name + ", type=" + type + ", media_id=" + media_id + "]";
    }
}
