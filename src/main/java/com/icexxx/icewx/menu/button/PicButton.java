package com.icexxx.icewx.menu.button;

import java.util.List;

import com.icexxx.icewx.menu.type.Pic;

/**
 * icewx &nbsp; 菜单图片按钮
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class PicButton implements WxButton {
    private String name;
    private Pic type;
    private String key;
    private List<WxButton> sub_button;

    public PicButton() {

    }

    public PicButton(String name, String key, Pic type) {
        this.name = name;
        this.key = key;
        this.type = type;
    }

    public PicButton(String name, Pic type, String key, List<WxButton> sub_button) {
        this.name = name;
        this.type = type;
        this.key = key;
        this.sub_button = sub_button;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pic getType() {
        return type;
    }

    public void setType(Pic type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<WxButton> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<WxButton> sub_button) {
        this.sub_button = sub_button;
    }

    @Override
    public String toString() {
        return "PicButton [name=" + name + ", type=" + type + ", key=" + key + ", sub_button=" + sub_button + "]";
    }
}
