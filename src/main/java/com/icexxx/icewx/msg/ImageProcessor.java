package com.icexxx.icewx.msg;

/**
 * icewx &nbsp; 图片接口(需重写)
 * 
 * @author IceWater
 * @version 1.0.0
 */
public interface ImageProcessor {
    public String reply(String mediaId, String userName, String createTime, String picUrl);
}
