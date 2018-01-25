package com.icexxx.icewx.msg;

/**
 * icewx &nbsp; 菜单扫码事件接口(需重写)
 * 
 * @author IceWater
 * @version 1.0.0
 */
public interface ScanEventProcessor {
    public String reply(String userName, String createTime, String eventKey, String ticket);
}
