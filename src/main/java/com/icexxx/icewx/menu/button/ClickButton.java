package com.icexxx.icewx.menu.button;

import com.icexxx.icewx.menu.type.Click;

/**
 * icewx &nbsp; 菜单点击按钮
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class ClickButton implements WxButton {
    private String name;
    private final Click type = Click.CLICK;
    private String key;
    private String message;

    public ClickButton() {

    }

    public ClickButton(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Click getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ClickButton [name=" + name + ", type=" + type + ", key=" + key + ", message=" + message + "]";
    }
}
