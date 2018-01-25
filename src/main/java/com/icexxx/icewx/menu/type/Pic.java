package com.icexxx.icewx.menu.type;

/**
 * icewx &nbsp; 菜单图片类型
 * 
 * @author IceWater
 * @version 1.0.0
 */
public enum Pic implements WxType {
    PIC_SYSPHOTO, PIC_PHOTO_OR_ALBUM, PIC_WEIXIN;
    @Override
    public String toString() {
        if (PIC_SYSPHOTO.equals(this)) {
            return "pic_sysphoto";
        } else if (PIC_PHOTO_OR_ALBUM.equals(this)) {
            return "pic_photo_or_album";
        } else if (PIC_WEIXIN.equals(this)) {
            return "pic_weixin";
        } else {
            throw new RuntimeException("不存在的微信菜单类型");
        }
    }
}
