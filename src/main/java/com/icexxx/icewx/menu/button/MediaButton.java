package com.icexxx.icewx.menu.button;

import com.icexxx.icewx.menu.type.Media;

/**
 * icewx &nbsp; 菜单资源按钮
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class MediaButton implements WxButton {
    private String name;
    private final Media type = Media.MEDIA_ID;
    private String media_id;

    public MediaButton() {

    }

    public MediaButton(String name, String media_id) {
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

    public Media getType() {
        return type;
    }

    @Override
    public String toString() {
        return "MediaButton [name=" + name + ", type=" + type + ", media_id=" + media_id + "]";
    }
}
