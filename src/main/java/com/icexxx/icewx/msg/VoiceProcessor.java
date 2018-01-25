package com.icexxx.icewx.msg;

/**
 * icewx &nbsp; 语音消息接口(需重写)
 * 
 * @author IceWater
 * @version 1.0.0
 */
public interface VoiceProcessor {
    public String reply(String mediaId, String userName, String createTime, String format, String recognition);
}
