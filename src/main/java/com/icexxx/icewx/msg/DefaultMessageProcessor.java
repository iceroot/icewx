package com.icexxx.icewx.msg;

import java.util.Date;

import com.icexxx.icewx.cont.Const;
import com.icexxx.icewx.util.IceWxUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

/**
 * icewx &nbsp; 默认的文本消息回复实现
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class DefaultMessageProcessor implements TextProcessor {

    @Override
    public String reply(String message, String userName, String createTime) {
        message = message.trim();
        if ("empty".equals(message)) {
            MessageSender.sendEmptyMessage();
            return null;
        }
        if ("null".equals(message)) {
            MessageSender.send("");
            return null;
        }
        if ("newLine".equals(message)) {
            MessageSender.send("abc\ndef\nghi\n\njklmn\ropqrst\r\nuvwxyz");
            return null;
        }
        if ("date".equalsIgnoreCase(message) || "日期".equals(message)) {
            return DateUtil.formatDate(new Date());
        } else if ("time".equalsIgnoreCase(message) || "时间".equals(message)) {
            return DateUtil.formatTime(new Date());
        }
        message = StrUtil.removeSuffix(message, "?");
        message = StrUtil.removeSuffix(message, "？");
        if ("今天星期几".equals(message) || "星期".equals(message) || "week".equals(message)) {
            return IceWxUtil.weekday();
        } else if (message.contains("圆周率")) {
            return "3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679";
        } else if ("白日依山尽".equals(message)) {
            return "黄河入海流";
        } else if ("apple".equals(message)) {
            return "苹果";
        } else if (Const.NOT_SUPPORT_MESSAGE.equals(message)) {
            return Const.NOT_SUPPORT_MESSAGE;
        }
        if (IceWxUtil.isFace(message)) {
            return "表情:" + IceWxUtil.convertFace(message);
        }
        return "你发送了:" + message;
    }

}
