package com.icexxx.icewx.msg.pojo;

/**
 * icewx &nbsp; 微信框架
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class WxCard {
    private String card_id;

    public WxCard() {

    }

    public WxCard(String card_id) {
        this.card_id = card_id;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    @Override
    public String toString() {
        return "WxCard [card_id=" + card_id + "]";
    }
}
