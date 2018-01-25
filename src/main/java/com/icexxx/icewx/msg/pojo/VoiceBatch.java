package com.icexxx.icewx.msg.pojo;

/**
 * icewx &nbsp; 微信框架
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class VoiceBatch {
    private Filter filter;
    private Voice voice;
    private final MsgType msgtype = MsgType.VOICE;

    public VoiceBatch() {

    }

    public VoiceBatch(Filter filter, Voice voice, String msgtype) {
        this.filter = filter;
        this.voice = voice;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }

    public MsgType getMsgtype() {
        return msgtype;
    }

    @Override
    public String toString() {
        return "VoiceBatch [filter=" + filter + ", voice=" + voice + ", msgtype=" + msgtype + "]";
    }
}
