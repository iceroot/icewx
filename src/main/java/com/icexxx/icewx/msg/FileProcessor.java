package com.icexxx.icewx.msg;

/**
 * icewx &nbsp; 发送文件接口(需重写)
 * 
 * @author IceWater
 * @version 1.0.0
 */
public interface FileProcessor {
    public String reply(String userName, String createTime, String title, String description, String fileKey,
            String fileMd5, String fileTotalLen);
}
