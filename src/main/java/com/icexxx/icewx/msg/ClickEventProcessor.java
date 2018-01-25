package com.icexxx.icewx.msg;

/**
 * icewx &nbsp; 菜单点击事件接口(需重写)
 * 
 * @author IceWater
 * @version 1.0.0
 */
public interface ClickEventProcessor {
    public String reply(String userName, String createTime, String eventKey);
}
