package com.icexxx.icewx.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cn.hutool.core.util.StrUtil;

/**
 * icewx &nbsp; 序列化工具类
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class IceSerializationUtil {

    public static byte[] encry(Map<String, String> map) {
        String keyValueSeparator = "\1";
        String pairSeparator = "\2";
        StringBuilder sb = new StringBuilder();
        Set<Entry<String, String>> entrySet = map.entrySet();
        for (Entry<String, String> entry : entrySet) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key);
            sb.append(keyValueSeparator);
            sb.append(value);
            sb.append(pairSeparator);
        }
        String result = sb.toString();
        if ("".equals(result)) {
            result = pairSeparator + pairSeparator;
        }
        return StrUtil.utf8Bytes(result);
    }

    public static Map<String, String> decry(byte[] bys) {
        String keyValueSeparator = "\1";
        String pairSeparator = "\2";
        String content = null;
        content = StrUtil.utf8Str(bys);
        Map<String, String> map = new HashMap<String, String>();
        if ((pairSeparator + pairSeparator).equals(content)) {
            return map;
        }
        content = StrUtil.removeSuffix(content, pairSeparator);
        String[] split = content.split(pairSeparator);
        for (int i = 0; i < split.length; i++) {
            String cur = split[i];
            String key = StrUtil.subBefore(cur, keyValueSeparator, false);
            String value = StrUtil.subAfter(cur, keyValueSeparator, false);
            map.put(key, value);
        }
        return map;
    }

}
