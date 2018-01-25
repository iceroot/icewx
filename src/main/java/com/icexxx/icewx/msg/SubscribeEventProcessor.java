package com.icexxx.icewx.msg;

/**
 * icewx &nbsp; 关注事件接口(需重写)
 * 
 * @author IceWater
 * @version 1.0.0
 */
public interface SubscribeEventProcessor {
    public String reply(String userName, String createTime);
}
