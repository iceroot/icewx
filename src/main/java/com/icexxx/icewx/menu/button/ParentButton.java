package com.icexxx.icewx.menu.button;

import java.util.List;

/**
 * icewx &nbsp; 菜单一级菜单按钮
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class ParentButton implements WxButton {
    private String name;
    private List<WxButton> sub_button;

    public ParentButton() {

    }

    public ParentButton(String name, List<WxButton> sub_button) {
        this.name = name;
        this.sub_button = sub_button;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WxButton> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<WxButton> sub_button) {
        this.sub_button = sub_button;
    }

    @Override
    public String toString() {
        return "ParentButton [name=" + name + ", sub_button=" + sub_button + "]";
    }
}
