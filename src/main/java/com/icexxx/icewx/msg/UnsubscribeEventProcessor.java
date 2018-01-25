package com.icexxx.icewx.msg;

/**
 * icewx &nbsp; 取消关注事件接口(需重写)
 * 
 * @author IceWater
 * @version 1.0.0
 */
public interface UnsubscribeEventProcessor {
    public String reply(String userName, String createTime);
}
