package com.icexxx.icewx.msg.pojo;

import java.util.List;

/**
 * icewx &nbsp; 微信框架
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class MpVideoUser {
    private List<String> touser;
    private MpVideo mpvideo;
    private final MsgType msgtype = MsgType.MPVIDEO;

    public MpVideoUser() {

    }

    public MpVideoUser(List<String> touser, MpVideo mpvideo, String msgtype) {
        this.touser = touser;
        this.mpvideo = mpvideo;
    }

    public List<String> getTouser() {
        return touser;
    }

    public void setTouser(List<String> touser) {
        this.touser = touser;
    }

    public MpVideo getMpvideo() {
        return mpvideo;
    }

    public void setMpvideo(MpVideo mpvideo) {
        this.mpvideo = mpvideo;
    }

    public MsgType getMsgtype() {
        return msgtype;
    }

    @Override
    public String toString() {
        return "MpVideoUser [touser=" + touser + ", mpvideo=" + mpvideo + ", msgtype=" + msgtype + "]";
    }
}
