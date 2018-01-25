package com.icexxx.icewx.msg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.icexxx.icewx.cont.Const;
import com.icexxx.icewx.service.IceServletContext;
import com.icexxx.icewx.service.MessageContext;
import com.icexxx.icewx.util.IceWxUtil;

/**
 * icewx &nbsp; 文本消息发送器
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class MessageSender {
    /**
     * 发送一个文本消息
     * 
     * @param message 需要发送的文本消息
     */
    public static void send(String message) {
        String fromUserName = MessageContext.getFromUserName();
        String toUserName = MessageContext.getToUserName();
        StringBuilder sb = new StringBuilder();
        String seconds = IceWxUtil.seconds();
        sb.append("<xml>");
        sb.append(IceWxUtil.wrapCDATA("FromUserName", fromUserName));
        sb.append(IceWxUtil.wrapCDATA("ToUserName", toUserName));
        sb.append(IceWxUtil.wrap("CreateTime", seconds));
        sb.append(IceWxUtil.wrapCDATA("MsgType", Const.TEXT));
        sb.append(IceWxUtil.wrapCDATA("Content", message));
        sb.append("</xml>");
        String xml = sb.toString();
        write(xml);
    }

    /**
     * 发送一个空消息
     */
    public static void sendEmptyMessage() {
        write("");
    }

    public static void write(String message) {
        HttpServletResponse response = IceServletContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.append(message);
        writer.flush();
        writer.close();
    }
}
