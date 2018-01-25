package com.icexxx.icewx.msg.pojo;

/**
 * icewx &nbsp; 微信框架
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class Text {
    private String content;

    public Text() {

    }

    public Text(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Text [content=" + content + "]";
    }
}
