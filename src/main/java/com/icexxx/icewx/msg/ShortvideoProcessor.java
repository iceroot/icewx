package com.icexxx.icewx.msg;

/**
 * icewx &nbsp; 小视频消息接口(需重写)
 * 
 * @author IceWater
 * @version 1.0.0
 */
public interface ShortvideoProcessor {
    public String reply(String mediaId, String userName, String createTime, String thumbMediaId);
}
