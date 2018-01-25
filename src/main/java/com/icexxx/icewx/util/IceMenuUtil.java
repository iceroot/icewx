package com.icexxx.icewx.util;

import com.icexxx.icewx.menu.button.ClickButton;
import com.icexxx.icewx.menu.button.LocationButton;
import com.icexxx.icewx.menu.button.PicButton;
import com.icexxx.icewx.menu.button.ScancodeButton;
import com.icexxx.icewx.menu.button.WxButton;

/**
 * icewx &nbsp; 菜单工具类
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class IceMenuUtil {
    public static boolean needKey(WxButton wxButton) {
        if (wxButton instanceof ClickButton) {
            return true;
        }
        if (wxButton instanceof PicButton) {
            return true;
        }
        if (wxButton instanceof ScancodeButton) {
            return true;
        }
        if (wxButton instanceof LocationButton) {
            return true;
        }
        return false;
    }

    public static boolean needKey(String type) {
        if ("click".equals(type)) {
            return true;
        }
        if ("pic_sysphoto".equals(type)) {
            return true;
        }
        if ("pic_photo_or_album".equals(type)) {
            return true;
        }
        if ("pic_weixin".equals(type)) {
            return true;
        }
        if ("scancode_waitmsg".equals(type)) {
            return true;
        }
        if ("scancode_push".equals(type)) {
            return true;
        }
        if ("location_select".equals(type)) {
            return true;
        }
        return false;
    }
}
