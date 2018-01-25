package com.icexxx.icewx.msg.pojo;

import java.util.List;

/**
 * icewx &nbsp; 微信框架
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class VoiceUser {
    private List<String> touser;
    private Voice voice;
    private final MsgType msgtype = MsgType.VOICE;

    public VoiceUser() {

    }

    public VoiceUser(List<String> touser, Voice voice, String msgtype) {
        this.touser = touser;
        this.voice = voice;
    }

    public List<String> getTouser() {
        return touser;
    }

    public void setTouser(List<String> touser) {
        this.touser = touser;
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
        return "VoiceUser [touser=" + touser + ", voice=" + voice + ", msgtype=" + msgtype + "]";
    }
}
