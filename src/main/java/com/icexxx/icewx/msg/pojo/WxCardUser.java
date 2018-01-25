package com.icexxx.icewx.msg.pojo;

import java.util.List;

/**
 * icewx &nbsp; 微信框架
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class WxCardUser {
    private List<String> touser;
    private WxCard wxcard;
    private final MsgType msgtype = MsgType.WXCARD;

    public WxCardUser() {

    }

    public WxCardUser(List<String> touser, WxCard wxcard, String msgtype) {
        this.touser = touser;
        this.wxcard = wxcard;
    }

    public List<String> getTouser() {
        return touser;
    }

    public void setTouser(List<String> touser) {
        this.touser = touser;
    }

    public WxCard getWxcard() {
        return wxcard;
    }

    public void setWxcard(WxCard wxcard) {
        this.wxcard = wxcard;
    }

    public MsgType getMsgtype() {
        return msgtype;
    }

    @Override
    public String toString() {
        return "WxCardUser [touser=" + touser + ", wxcard=" + wxcard + ", msgtype=" + msgtype + "]";
    }
}
