package com.icexxx.icewx.menu.type;

/**
 * icewx &nbsp; 菜单二维码类型
 * 
 * @author IceWater
 * @version 1.0.0
 */
public enum Scancode implements WxType {
    SCANCODE_PUSH, SCANCODE_WAITMSG;
    @Override
    public String toString() {
        if (SCANCODE_PUSH.equals(this)) {
            return "scancode_push";
        } else if (SCANCODE_WAITMSG.equals(this)) {
            return "scancode_waitmsg";
        } else {
            throw new RuntimeException("不存在的微信菜单类型");
        }
    }
}
