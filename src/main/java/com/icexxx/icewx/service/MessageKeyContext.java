package com.icexxx.icewx.service;

import java.util.HashMap;
import java.util.Map;

/**
 * icewx &nbsp; 消息应答上下文
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class MessageKeyContext {
    private static Map<String, String> map = new HashMap<String, String>();

    public static Map<String, String> getMap() {
        return map;
    }

    public static void setMap(Map<String, String> map) {
        MessageKeyContext.map = map;
    }

    public static String getKey(String key) {
        return map.get(key);
    }

    public static String put(String key, String value) {
        return map.put(key, value);
    }

    public static void clear() {
        map.clear();
    }
}
