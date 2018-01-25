package com.icexxx.icewx.msg.pojo;

/**
 * icewx &nbsp; 微信框架
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class WxCardBatch {
    private Filter filter;
    private WxCard wxcard;
    private final MsgType msgtype = MsgType.WXCARD;

    public WxCardBatch() {

    }

    public WxCardBatch(Filter filter, WxCard wxcard, String msgtype) {
        this.filter = filter;
        this.wxcard = wxcard;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
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
        return "WxCardBatch [filter=" + filter + ", wxcard=" + wxcard + ", msgtype=" + msgtype + "]";
    }
}
