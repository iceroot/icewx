package com.icexxx.icewx.msg.pojo;

/**
 * icewx &nbsp; 微信框架
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class Filter {
    private Boolean is_to_all;
    private Integer tag_id;

    public Filter() {

    }

    public Filter(Boolean is_to_all, Integer tag_id) {
        this.is_to_all = is_to_all;
        this.tag_id = tag_id;
    }

    public boolean isIs_to_all() {
        return is_to_all;
    }

    public void setIs_to_all(Boolean is_to_all) {
        this.is_to_all = is_to_all;
    }

    public Integer getTag_id() {
        return tag_id;
    }

    public void setTag_id(Integer tag_id) {
        this.tag_id = tag_id;
    }

    @Override
    public String toString() {
        return "Filter [is_to_all=" + is_to_all + ", tag_id=" + tag_id + "]";
    }
}
