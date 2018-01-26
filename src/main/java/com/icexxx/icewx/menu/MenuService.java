package com.icexxx.icewx.menu;

import java.util.List;
import java.util.Map;

import com.icexxx.icewx.cont.Const;
import com.icexxx.icewx.cont.IceWxUrl;
import com.icexxx.icewx.menu.button.ParentButton;
import com.icexxx.icewx.menu.button.WxButton;
import com.icexxx.icewx.menu.button.WxMenu;
import com.icexxx.icewx.menu.type.WxTypeConvert;
import com.icexxx.icewx.service.MessageKeyContext;
import com.icexxx.icewx.util.IceMenuUtil;
import com.icexxx.icewx.util.IceSerializationUtil;
import com.icexxx.icewx.util.IceWxUtil;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;

/**
 * icewx菜单生成
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class MenuService {
    private static final Log log = LogFactory.get();

    /**
     * 将Menu实体类转化为json
     * 
     * @param menu 菜单实体类
     * @return 转化后的json
     */
    public static String json(WxMenu menu) {
        JSONObject parseObj = JSONUtil.parseObj(menu);
        JSONArray button = parseObj.getJSONArray("button");
        List<WxButton> buttonListWx = menu.getButton();
        for (int i = 0; i < button.size(); i++) {
            JSONObject parentButton = JSONUtil.parseObj(button.get(i));
            JSONObject parentKey = parentButton.getJSONObject("key");
            WxButton parentButtonWx = buttonListWx.get(i);
            if (parentKey == null && IceMenuUtil.needKey(parentButtonWx)) {
                String key = genKey(menu, i);
                String msg = MessageKeyContext.getKey(key);
                if (msg != null) {
                    key = genKeyAgain(menu, i);
                    msg = MessageKeyContext.getKey(key);
                }
                parentButton.put("key", key);
                msg = parentButton.getStr("message");
                MessageKeyContext.put(key, msg);
            }
            if (!(parentButtonWx instanceof ParentButton)) {
                String type = WxTypeConvert.convert(parentButtonWx);
                parentButton.put("type", type);
                button.put(i, parentButton);
            }
            if (parentButton.containsKey("message")) {
                parentButton.remove("message");
            }
            JSONArray subButton = parentButton.getJSONArray("sub_button");
            if (subButton != null) {
                List<WxButton> subButtonList = ((ParentButton) parentButtonWx).getSub_button();
                for (int j = 0; j < subButton.size(); j++) {
                    JSONObject childButton = subButton.getJSONObject(j);
                    JSONObject childKey = childButton.getJSONObject("key");
                    WxButton childButtonWx = subButtonList.get(j);
                    if (childKey == null && IceMenuUtil.needKey(childButtonWx)) {
                        String key = genKey(menu, i, j);
                        String msg = MessageKeyContext.getKey(key);
                        if (msg != null) {
                            key = genKeyAgain(menu, i, j);
                            msg = MessageKeyContext.getKey(key);
                        }
                        childButton.put("key", key);
                        msg = childButton.getStr("message");
                        MessageKeyContext.put(key, msg);
                    }
                    String type = WxTypeConvert.convert(childButtonWx);
                    childButton.put("type", type);
                    if (childButton.containsKey("message")) {
                        childButton.remove("message");
                    }
                }
            }
        }
        return parseObj.toString();
    }

    private static String genKey(WxMenu menu, int parentndex, int childIndex) {
        List<WxButton> button = menu.getButton();
        WxButton wxButton = button.get(parentndex);
        if (wxButton instanceof ParentButton) {
            ParentButton parentButton = (ParentButton) wxButton;
            String nameParent = (String) ReflectUtil.getFieldValue(parentButton, "name");
            String typeParent = WxTypeConvert.convert(parentButton);
            List<WxButton> subButton = parentButton.getSub_button();
            WxButton childButton = subButton.get(childIndex);
            String nameChild = (String) ReflectUtil.getFieldValue(childButton, "name");
            String typeChild = WxTypeConvert.convert(childButton);
            String parentSign = nameParent + Const.SEPARATOR_FIELD + typeParent;
            String childSign = nameChild + Const.SEPARATOR_FIELD + typeChild;
            String hash = SecureUtil.md5(parentSign + Const.SEPARATOR_PARENT + childSign);
            hash = hash.substring(0, 16);
            return hash;
        }
        return null;
    }

    private static String genKey(WxMenu menu, int index) {
        List<WxButton> button = menu.getButton();
        WxButton wxButton = button.get(index);
        String name = (String) ReflectUtil.getFieldValue(wxButton, "name");
        String type = WxTypeConvert.convert(wxButton);
        String hash = SecureUtil.md5(name + Const.SEPARATOR_FIELD + type);
        hash = hash.substring(0, 16);
        return hash;
    }

    private static String genKeyAgain(WxMenu menu, int parentIndex, int childIndex) {
        List<WxButton> button = menu.getButton();
        WxButton wxButton = button.get(parentIndex);
        if (wxButton instanceof ParentButton) {
            ParentButton parentButton = (ParentButton) wxButton;
            String nameParent = (String) ReflectUtil.getFieldValue(parentButton, "name");
            String typeParent = WxTypeConvert.convert(parentButton);
            List<WxButton> subButton = parentButton.getSub_button();
            WxButton childButton = subButton.get(childIndex);
            String nameChild = (String) ReflectUtil.getFieldValue(childButton, "name");
            String typeChild = WxTypeConvert.convert(childButton);
            String parentSign = nameParent + Const.SEPARATOR_FIELD + typeParent + Const.SEPARATOR_INDEX + parentIndex;
            String childSign = nameChild + Const.SEPARATOR_FIELD + typeChild + Const.SEPARATOR_INDEX + childIndex;
            String hash = SecureUtil.md5(parentSign + Const.SEPARATOR_PARENT + childSign);
            hash = hash.substring(0, 16);
            return hash;
        }
        return null;
    }

    private static String genKeyAgain(WxMenu menu, int index) {
        List<WxButton> button = menu.getButton();
        WxButton wxButton = button.get(index);
        String name = (String) ReflectUtil.getFieldValue(wxButton, "name");
        String type = WxTypeConvert.convert(wxButton);
        String hash = SecureUtil.md5(name + Const.SEPARATOR_FIELD + type + Const.SEPARATOR_INDEX + index);
        hash = hash.substring(0, 16);
        return hash;
    }

    /**
     * 检查Menu实体类是否符合要求
     * 
     * @param menu 菜单实体类
     * @return 菜单是否满足要求
     */
    public static boolean check(WxMenu menu) {
        if (menu == null) {
            log.error("ERROR:menu为null");
            return false;
        }
        List<WxButton> button = menu.getButton();
        if (button == null) {
            log.error("ERROR:menu一级菜单列表为null");
            return false;
        }
        if (button.size() == 0) {
            log.error("ERROR:menu一级菜单列表为空");
            return false;
        }
        if (button.size() > 3) {
            log.warn("WARING:menu一级菜单列表数量多于3个");
            return false;
        }
        for (int i = 0; i < button.size(); i++) {
            WxButton wxButton = button.get(i);
            if (wxButton == null) {
                log.error("ERROR:menu一级菜单为null");
                return false;
            }
            Object name = ReflectUtil.getFieldValue(wxButton, "name");
            if (name == null) {
                log.error("ERROR:menu一级菜单名称为null");
                return false;
            }
            if ("".equals(name)) {
                log.error("ERROR:menu一级菜单名称为空字符串");
                return false;
            }
            if (wxButton instanceof ParentButton) {
                ParentButton parentButton = (ParentButton) wxButton;
                List<WxButton> parentButtonList = parentButton.getSub_button();
                if (parentButtonList == null) {
                    log.error("ERROR:子菜单列表为null");
                    return false;
                }
                int parentButtonListSize = parentButtonList.size();
                if (parentButtonListSize == 0) {
                    log.error("ERROR:子菜单个数为零");
                    return false;
                }
                if (parentButtonListSize > 5) {
                    log.error("ERROR:子菜单个数大于5个");
                    return false;
                }
                for (int j = 0; j < parentButtonListSize; j++) {
                    WxButton childButton = parentButtonList.get(j);
                    if (childButton == null) {
                        log.error("ERROR:子菜单为null");
                        return false;
                    }
                    if (childButton instanceof ParentButton) {
                        log.error("ERROR:子菜单不能为父菜单类型");
                        return false;
                    }
                    Object childName = ReflectUtil.getFieldValue(wxButton, "name");
                    if (childName == null) {
                        log.error("ERROR:menu子菜单名称为null");
                        return false;
                    }
                    if ("".equals(childName)) {
                        log.error("ERROR:menu子菜单名称为空字符串");
                        return false;
                    }
                }
            } else {
                Object nameOne = ReflectUtil.getFieldValue(wxButton, "name");
                if (nameOne == null) {
                    log.error("ERROR:menu一级菜单名称为null");
                    return false;
                }
                if ("".equals(nameOne)) {
                    log.error("ERROR:menu一级菜单名称为空字符串");
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean create(WxMenu menu) {
        boolean check = MenuService.check(menu);
        if (check) {
            String json = MenuService.json(menu);
            Map<String, String> map = MessageKeyContext.getMap();
            Map<String, String> urlToken = IceWxUtil.urlToken();
            String url = urlToken.get("url");
            String menuToken = urlToken.get("token");
            log.info("menuToken = {}", menuToken);
            boolean result = createMenu(json, url, menuToken, map);
            log.info(JSONUtil.formatJsonStr(json));
            return result;
        } else {
            log.error("菜单格式不正确");
            return false;
        }

    }

    private static boolean createMenu(String body, String url, String menuToken, Map<String, String> map) {
        String menuCreateUrl = IceWxUrl.MENU_CREATE;
        url = StrUtil.removeSuffix(url, "/");
        String accessTokenUrl = url + "/wx/accessToken?token=" + menuToken;
        String accessToken = HttpUtil.get(accessTokenUrl);
        menuCreateUrl = menuCreateUrl.replace("ACCESS_TOKEN", accessToken);
        String post = HttpUtil.post(menuCreateUrl, body, 5000);
        String menuUrl = StrUtil.subBefore(accessTokenUrl, "/wx/accessToken", false);
        byte[] binaryData = IceSerializationUtil.encry(map);
        String menuBody = Base64.encode(binaryData);
        menuBody = menuToken + menuBody;
        String MenuPost = HttpUtil.post(menuUrl, menuBody);
        log.info("MenuPost = {}", MenuPost);
        if (Const.MESSAGE_OK.equals(post)) {
            return true;
        } else {
            log.error(post);
            return false;
        }
    }
}
