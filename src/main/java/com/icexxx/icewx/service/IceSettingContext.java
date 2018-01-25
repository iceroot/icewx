package com.icexxx.icewx.service;

import cn.hutool.setting.Setting;

/**
 * icewx &nbsp; 配置文件操作上下文
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class IceSettingContext {
    private static Setting setting;

    public static Setting getSetting() {
        return setting;
    }

    public static void setSetting(Setting setting) {
        IceSettingContext.setting = setting;
    }

    public static String getSetting(String key) {
        return setting.getStr(key);
    }
}
