package com.icexxx.icewx.msg;

/**
 * icewx &nbsp; 链接消息接口(需重写)
 * 
 * @author IceWater
 * @version 1.0.0
 */
public interface LinkProcessor {
    public String reply(String userName, String createTime, String title, String description, String url);
}
