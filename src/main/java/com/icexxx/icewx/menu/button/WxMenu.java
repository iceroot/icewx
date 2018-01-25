package com.icexxx.icewx.menu.button;

import java.util.List;

/**
 * icewx &nbsp; 菜单整体对象实体类
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class WxMenu {
    private List<WxButton> button;

    public WxMenu() {

    }

    public WxMenu(List<WxButton> button) {
        this.button = button;
    }

    public List<WxButton> getButton() {
        return button;
    }

    public void setButton(List<WxButton> button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "WxMenu [button=" + button + "]";
    }
}
