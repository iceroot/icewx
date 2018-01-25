package com.icexxx.icewx.msg.pojo;

/**
 * icewx &nbsp; 微信框架
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class ImageBatch {
    private Filter filter;
    private Image image;
    private final MsgType msgtype = MsgType.IMAGE;

    public ImageBatch() {

    }

    public ImageBatch(Filter filter, Image image, String msgtype) {
        this.filter = filter;
        this.image = image;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
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
        return "ImageBatch [filter=" + filter + ", image=" + image + ", msgtype=" + msgtype + "]";
    }
}
