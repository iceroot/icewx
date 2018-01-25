package com.icexxx.icewx.msg.pojo;

/**
 * icewx &nbsp; 微信框架
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class ArticleBatch {
    private Filter filter;
    private MpNews mpnews;
    private final MsgType msgtype = MsgType.MPNEWS;
    private Integer send_ignore_reprint;

    public ArticleBatch() {

    }

    public ArticleBatch(Filter filter, MpNews mpnews, Integer send_ignore_reprint) {
        this.filter = filter;
        this.mpnews = mpnews;
        this.send_ignore_reprint = send_ignore_reprint;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public MpNews getMpnews() {
        return mpnews;
    }

    public void setMpnews(MpNews mpnews) {
        this.mpnews = mpnews;
    }

    public MsgType getMsgtype() {
        return msgtype;
    }

    public Integer getSend_ignore_reprint() {
        return send_ignore_reprint;
    }

    public void setSend_ignore_reprint(Integer send_ignore_reprint) {
        this.send_ignore_reprint = send_ignore_reprint;
    }

    @Override
    public String toString() {
        return "ArticleBatch [filter=" + filter + ", mpnews=" + mpnews + ", msgtype=" + msgtype
                + ", send_ignore_reprint=" + send_ignore_reprint + "]";
    }
}
