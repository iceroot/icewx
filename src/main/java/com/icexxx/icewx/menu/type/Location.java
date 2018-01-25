package com.icexxx.icewx.menu.type;

/**
 * icewx &nbsp; 菜单位置类型
 * 
 * @author IceWater
 * @version 1.0.0
 */
public enum Location implements WxType {
    LOCATION_SELECT;
    @Override
    public String toString() {
        return "location_select";
    }
}
