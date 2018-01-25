package com.icexxx.icewx.msg.pojo;

import java.util.List;

/**
 * icewx &nbsp; 微信框架
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class ArticleUser {
    private List<String> touser;
    private MpNews mpnews;
    private final MsgType msgtype = MsgType.MPNEWS;
    private Integer send_ignore_reprint;

    public ArticleUser() {

    }

    public ArticleUser(List<String> touser, MpNews mpnews, Integer send_ignore_reprint) {
        this.touser = touser;
        this.mpnews = mpnews;
        this.send_ignore_reprint = send_ignore_reprint;
    }

    public List<String> getTouser() {
        return touser;
    }

    public void setTouser(List<String> touser) {
        this.touser = touser;
    }

    public MpNews getMpnews() {
        return mpnews;
    }

    public void setMpnews(MpNews mpnews) {
        this.mpnews = mpnews;
    }

    public Integer getSend_ignore_reprint() {
        return send_ignore_reprint;
    }

    public void setSend_ignore_reprint(Integer send_ignore_reprint) {
        this.send_ignore_reprint = send_ignore_reprint;
    }

    public MsgType getMsgtype() {
        return msgtype;
    }

    @Override
    public String toString() {
        return "ArticleUser [touser=" + touser + ", mpnews=" + mpnews + ", msgtype=" + msgtype
                + ", send_ignore_reprint=" + send_ignore_reprint + "]";
    }
}
