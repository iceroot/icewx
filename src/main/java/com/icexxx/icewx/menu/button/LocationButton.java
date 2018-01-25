package com.icexxx.icewx.menu.button;

import com.icexxx.icewx.menu.type.Location;

/**
 * icewx &nbsp; 菜单发送位置按钮
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class LocationButton implements WxButton {
    private String name;
    private final Location type = Location.LOCATION_SELECT;
    private String key;

    public LocationButton() {

    }

    public LocationButton(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Location getType() {
        return type;
    }

    @Override
    public String toString() {
        return "LocationButton [name=" + name + ", type=" + type + ", key=" + key + "]";
    }
}
