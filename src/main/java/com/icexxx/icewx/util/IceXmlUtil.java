package com.icexxx.icewx.util;

import java.util.HashMap;
import java.util.Map;

import cn.hutool.core.util.StrUtil;

/**
 * icewx &nbsp; xml工具类
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class IceXmlUtil {

    public static Map<String, String> read(String xml) {
        String tag = "xml";
        xml = xml.trim();
        String prefix = StrUtil.wrap(tag, "<", ">");
        String suffix = StrUtil.wrap(tag, "</", ">");
        xml = StrUtil.removePrefix(xml, prefix);
        xml = StrUtil.removeSuffix(xml, suffix);
        Map<String, String> map = readXml(xml);
        return map;
    }

    private static Map<String, String> readXml(String xml) {
        Map<String, String> map = new HashMap<String, String>();
        xml = xml.trim();
        char[] chs = xml.toCharArray();
        StringBuilder tag = new StringBuilder();
        StringBuilder content = new StringBuilder();
        boolean isTag = false;
        boolean isCData = false;
        String tagStr = null;
        String contentStr = null;
        int lf = 0;
        for (int i = 0; i < chs.length; i++) {
            char chr = chs[i];
            if (chr == '<') {
                if (!isCData) {
                    isTag = true;
                    tag = new StringBuilder();
                    lf = 1;// <
                } else {
                    if (isTag) {
                        tag.append(chr);
                    } else {
                        content.append(chr);
                    }
                }
            } else if (chr == '/') {
                if (!isCData) {
                    if (lf == 1) {
                        if (i > 0 && chs[i - 1] == '<') {
                            lf = 2;// </
                            if (contentStr == null) {
                                contentStr = content.toString();
                            }
                            contentStr = escape(contentStr);
                            map.put(tagStr, contentStr);
                            contentStr = null;
                        } else {
                            if (isTag) {
                                tag.append(chr);
                            } else {
                                content.append(chr);
                            }
                        }
                    }
                } else {
                    if (isTag) {
                        tag.append(chr);
                    } else {
                        content.append(chr);
                    }
                }
            } else if (chr == '>') {
                if (!isCData) {
                    isTag = false;
                    if (lf == 1) {
                        content = new StringBuilder();
                        String tagStrTemp = tag.toString();
                        if (tagStrTemp.startsWith("![CDATA[") && tagStrTemp.endsWith("]]")) {
                            String cont = StrUtil.removePrefix(tagStrTemp, "![CDATA[");
                            cont = StrUtil.removeSuffix(cont, "]]");
                            contentStr = cont;
                        } else {
                            tagStr = tag.toString();
                        }
                    } else if (lf == 2) {
                        lf = 0;
                    }
                } else {
                    if (isTag) {
                        tag.append(chr);
                    } else {
                        content.append(chr);
                    }
                }
            } else {
                if (isTag) {
                    tag.append(chr);
                } else {
                    content.append(chr);
                }
                if (chr == '[') {
                    if (isCDataStart(chs, i)) {
                        isCData = true;
                    }
                } else if (chr == ']') {
                    if (isCDataEnd(chs, i)) {
                        isCData = false;
                    }
                }
            }
        }
        return map;
    }

    public static boolean isCDataStart(char[] chs, int index) {
        if (index >= chs.length) {
            return false;
        }
        if (index + 1 < "<![CDATA[".length()) {
            return false;
        } else {
            if (chs[index] != '[') {
                return false;
            }
            if (chs[index - 1] != 'A') {
                return false;
            }
            if (chs[index - 2] != 'T') {
                return false;
            }
            if (chs[index - 3] != 'A') {
                return false;
            }
            if (chs[index - 4] != 'D') {
                return false;
            }
            if (chs[index - 5] != 'C') {
                return false;
            }
            if (chs[index - 6] != '[') {
                return false;
            }
            if (chs[index - 7] != '!') {
                return false;
            }
            if (chs[index - 8] != '<') {
                return false;
            }
            return true;
        }

    }

    private static String escape(String str) {
        str = str.replace("&amp;", "&");
        str = str.replace("&lt;", "<");
        str = str.replace("&gt;", ">");
        str = str.replace("&quot;", "\"");
        str = str.replace("&nbsp;", " ");
        return str;
    }

    public static boolean isCDataEnd(char[] chs, int index) {
        if (index >= chs.length) {
            return false;
        }
        if (index + 2 < "]]>".length()) {
            return false;
        } else {
            if (chs[index] != ']') {
                return false;
            }
            if (chs[index - 1] != ']') {
                return false;
            }
            if (chs[index + 1] != '>') {
                return false;
            }
            return true;
        }
    }
}
