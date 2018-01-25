package com.icexxx.icewx.msg.pojo;

import java.util.List;

/**
 * icewx &nbsp; 微信框架
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class TextUser {
    private List<String> touser;
    private Text text;
    private final MsgType msgtype = MsgType.TEXT;

    public TextUser() {

    }

    public TextUser(List<String> touser, Text text, String msgtype) {
        this.touser = touser;
        this.text = text;
    }

    public List<String> getTouser() {
        return touser;
    }

    public void setTouser(List<String> touser) {
        this.touser = touser;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public MsgType getMsgtype() {
        return msgtype;
    }

    @Override
    public String toString() {
        return "TextUser [touser=" + touser + ", text=" + text + ", msgtype=" + msgtype + "]";
    }
}
