package com.icexxx.icewx.msg;

/**
 * icewx &nbsp; 位置消息接口(需重写)
 * 
 * @author IceWater
 * @version 1.0.0
 */
public interface LocationProcessor {
    public String reply(String userName, String createTime, String locationX, String locationY, String scale,
            String label);
}
