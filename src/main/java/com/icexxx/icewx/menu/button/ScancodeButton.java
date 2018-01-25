package com.icexxx.icewx.menu.button;

import java.util.List;

import com.icexxx.icewx.menu.type.Scancode;

/**
 * icewx &nbsp; 菜单扫描二维码按钮
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class ScancodeButton implements WxButton {
    private String name;
    private Scancode type;
    private String key;
    private List<WxButton> sub_button;

    public ScancodeButton() {

    }

    public ScancodeButton(String name, Scancode type, String key) {
        this.name = name;
        this.type = type;
        this.key = key;
    }

    public ScancodeButton(String name, Scancode type, String key, List<WxButton> sub_button) {
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

    public Scancode getType() {
        return type;
    }

    public void setType(Scancode type) {
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
        return "ScancodeButton [name=" + name + ", type=" + type + ", key=" + key + ", sub_button=" + sub_button + "]";
    }
}
