package com.icexxx.icewx.msg.pojo;

/**
 * icewx &nbsp; 微信框架
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class MpNews {
    private String media_id;

    public MpNews() {

    }

    public MpNews(String media_id) {
        this.media_id = media_id;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    @Override
    public String toString() {
        return "MpNews [media_id=" + media_id + "]";
    }
}
