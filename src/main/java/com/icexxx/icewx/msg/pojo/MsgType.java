package com.icexxx.icewx.msg.pojo;

/**
 * icewx &nbsp; 消息类型枚举
 * 
 * @author IceWater
 * @version 1.0.0
 */
public enum MsgType {
    MPNEWS, TEXT, VOICE, IMAGE, MPVIDEO, WXCARD;

    @Override
    public String toString() {
        if (MPNEWS.equals(this)) {
            return "mpnews";
        }
        if (TEXT.equals(this)) {
            return "text";
        }
        if (VOICE.equals(this)) {
            return "voice";
        }
        if (IMAGE.equals(this)) {
            return "image";
        }
        if (MPVIDEO.equals(this)) {
            return "mpvideo";
        }
        if (WXCARD.equals(this)) {
            return "wxcard";
        }
        return null;
    }

}
