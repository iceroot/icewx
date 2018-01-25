package com.icexxx.icewx.menu.type;

import com.icexxx.icewx.menu.button.ClickButton;
import com.icexxx.icewx.menu.button.LocationButton;
import com.icexxx.icewx.menu.button.MediaButton;
import com.icexxx.icewx.menu.button.MiniprogramButton;
import com.icexxx.icewx.menu.button.ParentButton;
import com.icexxx.icewx.menu.button.PicButton;
import com.icexxx.icewx.menu.button.ScancodeButton;
import com.icexxx.icewx.menu.button.ViewButton;
import com.icexxx.icewx.menu.button.ViewLimitedButton;
import com.icexxx.icewx.menu.button.WxButton;

/**
 * icewx &nbsp; 菜单类型名称转换器
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class WxTypeConvert {
    public static String convert(WxType type) {
        if (Click.CLICK.equals(type)) {
            return "click";
        } else if (View.VIEW.equals(type)) {
            return "view";
        } else if (Media.MEDIA_ID.equals(type)) {
            return "media_id";
        } else if (Location.LOCATION_SELECT.equals(type)) {
            return "location_select";
        } else if (Pic.PIC_SYSPHOTO.equals(type)) {
            return "pic_sysphoto";
        } else if (Pic.PIC_PHOTO_OR_ALBUM.equals(type)) {
            return "pic_photo_or_album";
        } else if (Pic.PIC_WEIXIN.equals(type)) {
            return "pic_weixin";
        } else if (Scancode.SCANCODE_PUSH.equals(type)) {
            return "scancode_push";
        } else if (Scancode.SCANCODE_WAITMSG.equals(type)) {
            return "scancode_waitmsg";
        } else if (ViewLimited.VIEW_LIMITED.equals(type)) {
            return "view_limited";
        } else if (Miniprogram.MINIPROGRAM.equals(type)) {
            return "miniprogram";
        } else {
            throw new RuntimeException("未知的微信菜单类型");
        }
    }

    public static String convert(WxButton wxButton) {
        if (wxButton instanceof ParentButton) {
            return "parent";
        } else if (wxButton instanceof ClickButton) {
            ClickButton clickButton = (ClickButton) wxButton;
            Click type = clickButton.getType();
            return convert(type);
        } else if (wxButton instanceof ViewButton) {
            ViewButton viewButton = (ViewButton) wxButton;
            View type = viewButton.getType();
            return convert(type);
        } else if (wxButton instanceof MediaButton) {
            MediaButton mediaButton = (MediaButton) wxButton;
            Media type = mediaButton.getType();
            return convert(type);
        } else if (wxButton instanceof LocationButton) {
            LocationButton locationButton = (LocationButton) wxButton;
            Location type = locationButton.getType();
            return convert(type);
        } else if (wxButton instanceof PicButton) {
            PicButton picButton = (PicButton) wxButton;
            Pic type = picButton.getType();
            return convert(type);
        } else if (wxButton instanceof ScancodeButton) {
            ScancodeButton scancodeButton = (ScancodeButton) wxButton;
            Scancode type = scancodeButton.getType();
            return convert(type);
        } else if (wxButton instanceof ViewLimitedButton) {
            ViewLimitedButton viewLimitedButton = (ViewLimitedButton) wxButton;
            ViewLimited type = viewLimitedButton.getType();
            return convert(type);
        } else if (wxButton instanceof MiniprogramButton) {
            MiniprogramButton miniprogramButton = (MiniprogramButton) wxButton;
            Miniprogram type = miniprogramButton.getType();
            return convert(type);
        } else {
            throw new RuntimeException("未知的微信菜单类型");
        }
    }
}
