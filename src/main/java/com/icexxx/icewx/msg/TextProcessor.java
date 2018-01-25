package com.icexxx.icewx.msg;

/**
 * icewx &nbsp; 文本消息接口(需重写)
 * 
 * @author IceWater
 * @version 1.0.0
 */
public interface TextProcessor {
    public String reply(String message, String userName, String createTime);
}
