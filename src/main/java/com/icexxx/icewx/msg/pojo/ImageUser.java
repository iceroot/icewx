package com.icexxx.icewx.msg.pojo;

import java.util.List;

/**
 * icewx &nbsp; 微信框架
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class ImageUser {
    private List<String> touser;
    private Image image;
    private final MsgType msgtype = MsgType.IMAGE;

    public ImageUser() {

    }

    public ImageUser(List<String> touser, Image image, String msgtype) {
        this.touser = touser;
        this.image = image;
    }

    public List<String> getTouser() {
        return touser;
    }

    public void setTouser(List<String> touser) {
        this.touser = touser;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public MsgType getMsgtype() {
        return msgtype;
    }

    @Override
    public String toString() {
        return "ImageUser [touser=" + touser + ", image=" + image + ", msgtype=" + msgtype + "]";
    }
}
