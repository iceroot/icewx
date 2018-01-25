package com.icexxx.icewx.service;

/**
 * icewx &nbsp; 发送消息上下文
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class MessageContext {
    private static String toUserName;
    private static String fromUserName;
    private static String createTime;
    private static String msgType;
    private static String msgId;
    private static String lastMsgId;

    public static String getToUserName() {
        return toUserName;
    }

    public static void setToUserName(String toUserName) {
        MessageContext.toUserName = toUserName;
    }

    public static String getFromUserName() {
        return fromUserName;
    }

    public static void setFromUserName(String fromUserName) {
        MessageContext.fromUserName = fromUserName;
    }

    public static String getCreateTime() {
        return createTime;
    }

    public static void setCreateTime(String createTime) {
        MessageContext.createTime = createTime;
    }

    public static String getMsgType() {
        return msgType;
    }

    public static void setMsgType(String msgType) {
        MessageContext.msgType = msgType;
    }

    public static String getMsgId() {
        return msgId;
    }

    public static void setMsgId(String msgId) {
        MessageContext.msgId = msgId;
    }

    public static String getLastMsgId() {
        return lastMsgId;
    }

    public static void setLastMsgId(String lastMsgId) {
        MessageContext.lastMsgId = lastMsgId;
    }

}
