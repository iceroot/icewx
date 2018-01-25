package com.icexxx.icewx.msg.pojo;

/**
 * icewx &nbsp; 微信框架
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class MpVideoBatch {
    private Filter filter;
    private MpVideo mpvideo;
    private final MsgType msgtype = MsgType.MPVIDEO;

    public MpVideoBatch() {

    }

    public MpVideoBatch(Filter filter, MpVideo mpvideo, String msgtype) {
        this.filter = filter;
        this.mpvideo = mpvideo;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
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
        return "MpVideoBatch [filter=" + filter + ", mpvideo=" + mpvideo + ", msgtype=" + msgtype + "]";
    }
}
