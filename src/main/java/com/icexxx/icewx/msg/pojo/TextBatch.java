package com.icexxx.icewx.msg.pojo;

/**
 * icewx &nbsp; 微信框架
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class TextBatch {
    private Filter filter;
    private Text text;
    private final MsgType msgtype = MsgType.TEXT;

    public TextBatch() {

    }

    public TextBatch(Filter filter, Text text, String msgtype) {
        this.filter = filter;
        this.text = text;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
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
        return "TextBatch [filter=" + filter + ", text=" + text + ", msgtype=" + msgtype + "]";
    }
}
