package com.icexxx.icewx.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.icexxx.icewx.cont.Const;
import com.icexxx.icewx.cont.IceWxUrl;
import com.icexxx.icewx.msg.MessageSender;
import com.icexxx.icewx.msg.pojo.Filter;
import com.icexxx.icewx.msg.pojo.Text;
import com.icexxx.icewx.msg.pojo.TextBatch;
import com.icexxx.icewx.msg.pojo.TextUser;
import com.icexxx.icewx.service.IceWxContext;
import com.icexxx.icewx.service.MessageKeyContext;
import com.icexxx.icewx.user.BatchUserInfo;
import com.icexxx.icewx.user.UserInfo;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Week;
import cn.hutool.core.exceptions.UtilException;
import cn.hutool.http.HttpUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;

/**
 * icewx &nbsp; 微信核心工具类
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class IceWxUtil {

    private static final Log log = LogFactory.get();

    public static boolean checkSign(String token, String timestamp, String nonce, String signature) {
        String[] array = new String[] { token, timestamp, nonce };
        Arrays.sort(array);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
        }
        String sign = SecureUtil.sha1(sb.toString());
        return sign.equals(signature);
    }

    public static String seconds() {
        long now = System.currentTimeMillis();
        now = now / 1000;
        return String.valueOf(now);
    }

    public static String date(String secondStr) {
        return DateUtil.date(Long.parseLong(secondStr) * 1000).toString();
    }

    public static String wrap(String tagName, String text) {
        String prefix = StrUtil.wrap(tagName, "<", ">");
        String suffix = StrUtil.wrap(tagName, "</", ">");
        return StrUtil.wrap(text, prefix, suffix);
    }

    public static String wrapCDATA(String tagName, String text) {
        String prefix = StrUtil.wrap(tagName, "<", ">");
        String suffix = StrUtil.wrap(tagName, "</", ">");
        String result = StrUtil.wrap(text, "<![CDATA[", "]]>");
        result = StrUtil.wrap(result, prefix, suffix);
        return result;
    }

    public static String removeTag(String jsp) {
        if (StrUtil.isNotBlank(jsp)) {
            int indexOf = jsp.indexOf("<%@");
            if (indexOf != -1) {
                int rightIndex = jsp.indexOf("%>", indexOf + "<%@".length());
                return jsp.substring(rightIndex + "%>".length());
            }
        }
        return jsp;
    }

    public static <T> T newInstance(String className) {
        if (StrUtil.isBlank(className) || Const.NONE.equals(className)) {
            return null;
        }
        try {
            T newInstance = ReflectUtil.newInstance(className);
            return newInstance;
        } catch (UtilException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.startsWith("Instance class [") && errorMessage.endsWith("] error!")) {
                return null;
            } else {
                throw e;
            }
        }
    }

    public static boolean isUtf8(String charset) {
        if (StrUtil.isBlank(charset)) {
            return false;
        }
        charset = charset.toLowerCase();
        if ("utf-8".equals(charset) || "utf8".equals(charset)) {
            return true;
        }
        return false;
    }

    public static String charset(String jsp) {
        if (StrUtil.isNotBlank(jsp)) {
            int indexOf = jsp.indexOf("<%@");
            if (indexOf != -1) {
                int rightIndex = jsp.indexOf("%>", indexOf + "<%@".length());
                if (rightIndex != -1) {
                    String jspHead = jsp.substring(indexOf, rightIndex + "%>".length());
                    int pageEncodingIndex = jspHead.indexOf("pageEncoding");
                    if (pageEncodingIndex != -1) {
                        jspHead = jspHead.substring(pageEncodingIndex);
                        jspHead = StrUtil.removeSuffix(jspHead, "%>");
                        jspHead = jspHead.trim();
                        int leftEqualsIndex = jspHead.indexOf("=");
                        int rightEqualsIndex = jspHead.lastIndexOf("=");
                        if (leftEqualsIndex != -1 && rightEqualsIndex != -1) {
                            String pageEncoding = null;
                            if (leftEqualsIndex == rightEqualsIndex) {
                                pageEncoding = StrUtil.subAfter(jspHead, "=", false);
                                pageEncoding = pageEncoding.trim();
                                pageEncoding = StrUtil.removePrefix(pageEncoding, "\"");
                                pageEncoding = StrUtil.removeSuffix(pageEncoding, "\"");
                            } else {
                                pageEncoding = jspHead.substring(leftEqualsIndex + 1, rightEqualsIndex);
                                pageEncoding = pageEncoding.trim();
                                pageEncoding = StrUtil.removePrefix(pageEncoding, "\"");
                                if (pageEncoding.contains(" ")) {
                                    pageEncoding = StrUtil.subBefore(pageEncoding, " ", false);
                                    pageEncoding = StrUtil.removeSuffix(pageEncoding, "\"");
                                } else {
                                    pageEncoding = StrUtil.subBefore(pageEncoding, "\"", false);
                                }
                            }
                            pageEncoding = pageEncoding.trim();
                            if (StrUtil.isNotBlank(pageEncoding)) {
                                return pageEncoding;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public static String weekday() {
        Calendar calendar = Calendar.getInstance();
        return Week.of(calendar.get(Calendar.DAY_OF_WEEK)).toChinese();
    }

    public static void jumpJsp(HttpServletResponse response, String pathTranslated) throws IOException {
        if (StrUtil.isNotBlank(pathTranslated)) {
            pathTranslated = pathTranslated.replace("\\", "/");
            String pathTranslatedNew = pathTranslated;
            if (!FileUtil.isFile(pathTranslated)) {
                pathTranslatedNew = pathTranslated + "index.jsp";
            }
            if (!FileUtil.isFile(pathTranslatedNew)) {
                pathTranslatedNew = pathTranslated + "index.html";
            }
            if (!FileUtil.isFile(pathTranslatedNew)) {
                pathTranslatedNew = pathTranslated + "index.htm";
            }
            if (!FileUtil.isFile(pathTranslatedNew)) {
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                writer.append(Const.INDEX_CONTENT);
                writer.flush();
                writer.close();
                return;
            }
            String jsp = FileUtil.readUtf8String(pathTranslatedNew);
            String charset = IceWxUtil.charset(jsp);
            if (StrUtil.isNotBlank(charset) && !IceWxUtil.isUtf8(charset)) {
                jsp = FileUtil.readString(pathTranslated, charset);
            }
            jsp = IceWxUtil.removeTag(jsp);
            response.getWriter().append(jsp);
        } else {
            response.getWriter().append("index");
        }
    }

    public static CharSequence link(String link) {
        StringBuilder sb = new StringBuilder();
        sb.append("<a ");
        sb.append("href = \"");
        sb.append(link);
        sb.append("\">");
        sb.append(link);
        sb.append("</a>");
        return sb.toString();
    }

    public static String getMessageByUrl(String suffix, String queryString) {
        String accessToken = IceWxContext.getAccessToken();
        if ("accessToken".equals(suffix)) {
            return accessToken;
        }
        String url = null;
        if ("getCallbackIp".equals(suffix)) {
            url = IceWxUrl.GET_CALLBACK_IP;
        } else if ("getUserList".equals(suffix)) {
            url = IceWxUrl.USER_GET_LIST;
            url = StrUtil.removeSuffix(url, "&next_openid=NEXT_OPENID");
        } else if ("getUserInfo".equals(suffix)) {
            url = replaceOpenid(queryString, IceWxUrl.USER_INFO);
        } else if ("getUserInfoBatch".equals(suffix)) {
            Map<String, String> map = IceWxUtil.map(queryString);
            url = IceWxUrl.USER_INFO_BATCH;
            url = url.replace("ACCESS_TOKEN", accessToken);
            String openids = map.get("openid");
            if (StrUtil.isBlank(openids)) {
                throw new RuntimeException("批量获取用户接口url参数openid不存在");
            }
            String body = openIdsJson(openids);
            String post = HttpUtil.post(url, body);
            return post;
        } else if ("setUserRemark".equals(suffix)) {
            Map<String, String> map = IceWxUtil.map(queryString);
            url = IceWxUrl.USER_REMARK;
            url = url.replace("ACCESS_TOKEN", accessToken);
            String openid = map.get("openid");
            String remark = map.get("remark");
            if (StrUtil.isBlank(openid)) {
                throw new RuntimeException("设置用户备注接口url参数openid不存在");
            }
            if (StrUtil.isBlank(remark)) {
                throw new RuntimeException("设置用户备注接口url参数remark不存在");
            }
            String body = openIdsJson(openid, remark);
            String post = HttpUtil.post(url, body);
            return post;
        } else if ("sendTextBatch".equals(suffix)) {
            Map<String, String> map = IceWxUtil.map(queryString);
            url = IceWxUrl.MASS_SENDALL;
            url = url.replace("ACCESS_TOKEN", accessToken);
            String tagId = map.get("tagId");
            String content = map.get("content");
            Integer tagIdNum = Convert.toInt(tagId);
            if (StrUtil.isBlank(content)) {
                throw new RuntimeException("根据标签群发消息接口url参数content不存在");
            }
            String body = null;
            if (StrUtil.isBlank(tagId)) {
                body = textBatchJson(content);
            } else {
                body = textBatchJson(content, tagIdNum);
            }
            String post = HttpUtil.post(url, body);
            return post;
        } else if ("sendTextUser".equals(suffix)) {
            Map<String, String> map = IceWxUtil.map(queryString);
            url = IceWxUrl.MASS_SEND;
            url = url.replace("ACCESS_TOKEN", accessToken);
            String openids = map.get("openid");
            String content = map.get("content");
            String[] users = openids.split(",");
            List<String> touser = CollUtil.newArrayList(users);
            if (StrUtil.isBlank(content)) {
                throw new RuntimeException("根据标签群发消息接口url参数content不存在");
            }
            String body = null;
            body = textUserJson(content, touser);
            String post = HttpUtil.post(url, body);
            return post;
        } else if ("menuDelconditional".equals(suffix)) {
            return post(IceWxUrl.MENU_DELCONDITIONAL, queryString, accessToken, "menuid");
        } else if ("menuTrymatch".equals(suffix)) {
            return post(IceWxUrl.MENU_TRYMATCH, queryString, accessToken, "user_id");
        } else if ("templateSetIndustry".equals(suffix)) {
            return post(IceWxUrl.TEMPLATE_SET_INDUSTRY, queryString, accessToken, "industry_id1", "industry_id2");
        } else if ("templateAddTemplate".equals(suffix)) {
            return post(IceWxUrl.TEMPLATE_ADD_TEMPLATE, queryString, accessToken, "template_id_short");
        } else if ("templateDel".equals(suffix)) {
            return post(IceWxUrl.TEMPLATE_DEL, queryString, accessToken, "template_id");
        } else if ("materialGet".equals(suffix)) {
            return post(IceWxUrl.MATERIAL_GET, queryString, accessToken, "media_id");
        } else if ("materialDel".equals(suffix)) {
            return post(IceWxUrl.MATERIAL_DEL, queryString, accessToken, "media_id");
        } else if ("tagsGetblacklist".equals(suffix)) {
            return post(IceWxUrl.TAGS_GETBLACKLIST, queryString, accessToken, "begin_openid");
        } else if ("tagsBatchblacklist".equals(suffix)) {
            return postList(IceWxUrl.TAGS_BATCHBLACKLIST, queryString, accessToken, "openid_list");
        } else if ("tagsBatchunblacklist".equals(suffix)) {
            return postList(IceWxUrl.TAGS_BATCHUNBLACKLIST, queryString, accessToken, "openid_list");
        }
        if ("getMenuList".equals(suffix)) {
            url = IceWxUrl.MENU_GET_LIST;
        } else if ("menuDelete".equals(suffix)) {
            url = IceWxUrl.MENU_DELETE;
        } else if ("getCurrentSelfmenuInfo".equals(suffix)) {
            url = IceWxUrl.GET_CURRENT_SELFMENU_INFO;
        } else if ("kfaccountDel".equals(suffix)) {
            url = IceWxUrl.KFACCOUNT_DEL;
        } else if ("kfaccountGetkflist".equals(suffix)) {
            url = IceWxUrl.KFACCOUNT_GETKFLIST;
        } else if ("templateGetIndustry".equals(suffix)) {
            url = IceWxUrl.TEMPLATE_GET_INDUSTRY;
        } else if ("templateGetList".equals(suffix)) {
            url = IceWxUrl.TEMPLATE_GET_LIST;
        } else if ("autoreplyInfo".equals(suffix)) {
            url = IceWxUrl.AUTOREPLY_INFO;
        } else if ("cardGetticket".equals(suffix)) {
            url = IceWxUrl.CARD_GETTICKET;
        } else if ("materialCount".equals(suffix)) {
            url = IceWxUrl.MATERIAL_COUNT;
        } else if ("tagsGet".equals(suffix)) {
            url = IceWxUrl.TAGS_GET;
        } else if ("tagsUserget".equals(suffix)) {
            url = IceWxUrl.TAGS_USERGET;
        } else if ("snsUserinfo".equals(suffix)) {
            url = replaceOpenid(queryString, IceWxUrl.SNS_USERINFO);
        } else if ("snsCheck".equals(suffix)) {
            url = replaceOpenid(queryString, IceWxUrl.SNS_CHECK);
        } else if ("mediaGet".equals(suffix)) {
            url = replaceOpenid(queryString, IceWxUrl.MEDIA_GET, "media_id", "MEDIA_ID");
        } else if ("mediaHighDefinitionVoice".equals(suffix)) {
            url = replaceOpenid(queryString, IceWxUrl.MEDIA_HIGH_DEFINITION_VOICE, "media_id", "MEDIA_ID");
        } else if ("qrcodeShowqrcode".equals(suffix)) {
            url = replaceOpenid(queryString, IceWxUrl.MEDIA_GET, "ticket", "TICKET");
        }
        if (url == null) {
            return null;
        }
        String result = get(url, accessToken);
        return result;
    }

    private static String postList(String url, String queryString, String accessToken, String keyName) {
        Map<String, String> map = IceWxUtil.map(queryString);
        url = url.replace("ACCESS_TOKEN", accessToken);
        String value = map.get(keyName);
        if (StrUtil.isBlank(value)) {
            throw new RuntimeException("接口url参数" + keyName + "不存在");
        }
        String[] array = value.split(",");
        String json = JSONUtil.toJsonStr(array);
        String body = null;
        body = "{\"keyName\":value}";
        body = body.replace("keyName", keyName);
        body = body.replace("value", json);
        String post = HttpUtil.post(url, body);
        return post;
    }

    private static String post(String url, String queryString, String accessToken, String keyName) {
        Map<String, String> map = IceWxUtil.map(queryString);
        url = url.replace("ACCESS_TOKEN", accessToken);
        String value = map.get(keyName);
        if (StrUtil.isBlank(value)) {
            throw new RuntimeException("接口url参数" + keyName + "不存在");
        }
        String body = null;
        body = "{\"keyName\":\"value\"}";
        body = body.replace("keyName", keyName);
        body = body.replace("value", value);
        String post = HttpUtil.post(url, body);
        return post;
    }

    private static String post(String url, String queryString, String accessToken, String keyName1, String keyName2) {
        Map<String, String> map = IceWxUtil.map(queryString);
        url = url.replace("ACCESS_TOKEN", accessToken);
        String value1 = map.get(keyName1);
        String value2 = map.get(keyName2);
        if (StrUtil.isBlank(value1)) {
            throw new RuntimeException("接口url参数" + keyName1 + "不存在");
        }
        if (StrUtil.isBlank(value2)) {
            throw new RuntimeException("接口url参数" + keyName2 + "不存在");
        }
        String body = null;
        body = "{\"keyName1\":\"value1\",\"keyName2\":\"value2\"}";
        body = body.replace("keyName1", keyName1);
        body = body.replace("value1", value1);
        body = body.replace("keyName2", keyName2);
        body = body.replace("value2", value2);
        String post = HttpUtil.post(url, body);
        return post;
    }

    private static String replaceOpenid(String queryString, String url) {
        Map<String, String> map = IceWxUtil.map(queryString);
        String openid = map.get("openid");
        url = url.replace("OPENID", openid);
        return url;
    }

    private static String replaceOpenid(String queryString, String url, String keyName, String valueName) {
        Map<String, String> map = IceWxUtil.map(queryString);
        String value = map.get(keyName);
        url = url.replace(valueName, value);
        return url;
    }

    private static String openIdsJson(String openid, String remark) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("openid", openid);
        remark = StrUtil.subPre(remark, 30);
        map.put("remark", remark);
        return JSONUtil.toJsonStr(map);
    }

    private static String textBatchJson(String content) {
        Filter filter = new Filter();
        filter.setIs_to_all(true);
        Text text = new Text(content);
        TextBatch textBatch = new TextBatch();
        textBatch.setFilter(filter);
        textBatch.setText(text);
        return JSONUtil.toJsonStr(textBatch);
    }

    private static String textBatchJson(String content, Integer tagId) {
        Filter filter = new Filter();
        filter.setIs_to_all(false);
        filter.setTag_id(tagId);
        Text text = new Text(content);
        TextBatch textBatch = new TextBatch();
        textBatch.setFilter(filter);
        textBatch.setText(text);
        return JSONUtil.toJsonStr(textBatch);
    }

    private static String textUserJson(String content, List<String> touser) {
        Text text = new Text(content);
        TextUser textUser = new TextUser();
        textUser.setTouser(touser);
        textUser.setText(text);
        return JSONUtil.toJsonStr(textUser);
    }

    private static String openIdsJson(String openids) {
        if (StrUtil.isBlank(openids)) {
            return null;
        }
        String[] openidsArray = openids.split(",");
        BatchUserInfo batchUserInfo = new BatchUserInfo();
        List<UserInfo> userList = new ArrayList<UserInfo>();
        for (int i = 0; i < openidsArray.length; i++) {
            String openId = openidsArray[i];
            if (StrUtil.isNotBlank(openId)) {
                UserInfo userInfo = new UserInfo();
                userInfo.setOpenid(openId);
                userInfo.setLang(Const.ZH_CN);
                userList.add(userInfo);
            }
        }
        batchUserInfo.setUser_list(userList);
        return JSONUtil.toJsonStr(batchUserInfo);
    }

    private static String get(String url, String accessToken) {
        url = url.replace("ACCESS_TOKEN", accessToken);
        String result = HttpUtil.get(url);
        return result;
    }

    public static void save(String message) {
        String token = message.substring(0, 32);
        String base64 = message.substring(32);
        String tokenHashValue = IceWxUtil.token();
        if (StrUtil.isNotBlank(token) && token.equals(tokenHashValue)) {
            byte[] data = Base64.decode(base64);
            String fileName = IceWxContext.getMessageSave();
            FileUtil.mkParentDirs(fileName);
            Map<String, String> map = IceSerializationUtil.decry(data);
            MessageKeyContext.setMap(map);
            FileUtil.writeBytes(data, fileName);
        }
    }

    public static Map<String, String> read() {
        String fileName = IceWxContext.getMessageSave();
        if (!FileUtil.exist(fileName)) {
            return new HashMap<String, String>();
        }
        byte[] readBytes = FileUtil.readBytes(fileName);
        Map<String, String> map = IceSerializationUtil.decry(readBytes);
        return map;
    }

    public static String getMessageSaveFile() {
        String messageSave = IceWxContext.getMessageSave();
        if (StrUtil.isBlank(messageSave)) {
            messageSave = defaultMessageSavePath();
            FileUtil.mkParentDirs(messageSave);
        }
        messageSave = messageSave.replace("\\", "/");
        if (messageSave.endsWith("/")) {
            messageSave += "msgdata.txt";
        }
        IceWxContext.setMessageSave(messageSave);
        return messageSave;
    }

    private static String defaultMessageSavePath() {
        boolean isWindow = FileUtil.isWindows();
        if (isWindow) {
            if (FileUtil.exist("C:/ProgramData")) {
                return "C:/ProgramData/icewx/msgdata.txt";
            }
            File[] listRoots = File.listRoots();
            for (int i = 0; i < listRoots.length; i++) {
                File folder = listRoots[i];
                if (folder.canWrite()) {
                    String disk = folder.getAbsolutePath();
                    disk = disk.replace("\\", "/");
                    disk = StrUtil.removeSuffix(disk, "/");
                    disk += "/icewx/msgdata.txt";
                    return disk;
                }
            }
        } else {
            return "/usr/local/icewx/msgdata.txt";
        }
        return null;
    }

    public static boolean isFace(String message) {
        if ("/:cake".equals(message)) {
            return true;
        } else if ("/:bome".equals(message)) {
            return true;
        } else if ("/:shit".equals(message)) {
            return true;
        } else if ("/:moon".equals(message)) {
            return true;
        } else if ("/:sun".equals(message)) {
            return true;
        } else if ("/:hug".equals(message)) {
            return true;
        } else if ("/:strong".equals(message)) {
            return true;
        } else if ("/:weak".equals(message)) {
            return true;
        } else if ("/:share".equals(message)) {
            return true;
        } else if ("/:v".equals(message)) {
            return true;
        } else if ("/:@)".equals(message)) {
            return true;
        } else if ("/:jj".equals(message)) {
            return true;
        } else if ("/:@@".equals(message)) {
            return true;
        } else if ("/:ok".equals(message)) {
            return true;
        } else if ("/:jump".equals(message)) {
            return true;
        } else if ("/:shake".equals(message)) {
            return true;
        } else if ("/:<O>".equals(message)) {
            return true;
        } else if ("/:circle".equals(message)) {
            return true;
        } else if ("/:xx".equals(message)) {
            return true;
        } else if ("/::+".equals(message)) {
            return true;
        } else if ("/::*".equals(message)) {
            return true;
        } else if ("/::<".equals(message)) {
            return true;
        } else if ("/::>".equals(message)) {
            return true;
        } else if ("/::@".equals(message)) {
            return true;
        } else if ("/::~".equals(message)) {
            return true;
        } else if ("/:B-)".equals(message)) {
            return true;
        } else if ("/::L".equals(message)) {
            return true;
        } else if ("/:handclap".equals(message)) {
            return true;
        } else if ("/:coffee".equals(message)) {
            return true;
        } else if ("/:,@f".equals(message)) {
            return true;
        } else if ("[Hey]".equals(message)) {
            return true;
        } else if ("[Facepalm]".equals(message)) {
            return true;
        } else if ("[Smirk]".equals(message)) {
            return true;
        } else if ("[Smart]".equals(message)) {
            return true;
        } else if ("[Concerned]".equals(message)) {
            return true;
        } else if ("[Yeah!]".equals(message)) {
            return true;
        } else if ("[Packet]".equals(message)) {
            return true;
        } else if ("[Chick]".equals(message)) {
            return true;
        } else {
            if (message.length() != 1) {
                return false;
            }
            int code = message.charAt(0);
            if (code == 58389) {
                return true;
            } else if (code == 58380) {
                return true;
            } else if (code == 58386) {
                return true;
            } else if (code == 58377) {
                return true;
            } else if (code == 58381) {
                return true;
            } else if (code == 57607) {
                return true;
            } else if (code == 58371) {
                return true;
            } else if (code == 58382) {
                return true;
            } else if (code == 57627) {
                return true;
            } else if (code == 58397) {
                return true;
            } else if (code == 57676) {
                return true;
            } else if (code == 58130) {
                return true;
            } else if (code == 57618) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static String convertFace(String message) {
        if ("/:cake".equals(message)) {
            return "cake";
        } else if ("/:bome".equals(message)) {
            return "bome";
        } else if ("/:shit".equals(message)) {
            return "shit";
        } else if ("/:moon".equals(message)) {
            return "moon";
        } else if ("/:sun".equals(message)) {
            return "sun";
        } else if ("/:hug".equals(message)) {
            return "hug";
        } else if ("/:strong".equals(message)) {
            return "strong";
        } else if ("/:weak".equals(message)) {
            return "weak";
        } else if ("/:share".equals(message)) {
            return "share";
        } else if ("/:v".equals(message)) {
            return "v";
        } else if ("/:@)".equals(message)) {
            return "@)";
        } else if ("/:jj".equals(message)) {
            return "/:jj";
        } else if ("/:@@".equals(message)) {
            return "@@";
        } else if ("/:ok".equals(message)) {
            return "ok";
        } else if ("/:jump".equals(message)) {
            return "jump";
        } else if ("/:shake".equals(message)) {
            return "shake";
        } else if ("/:<O>".equals(message)) {
            return "<O>";
        } else if ("/:circle".equals(message)) {
            return "circle";
        } else if ("/:xx".equals(message)) {
            return "[敲打]";
        } else if ("/::+".equals(message)) {
            return "[酷]";
        } else if ("/::*".equals(message)) {
            return "/::*";
        } else if ("/::<".equals(message)) {
            return "[流泪]";
        } else if ("/::>".equals(message)) {
            return "/::>";
        } else if ("/::@".equals(message)) {
            return "/::@";
        } else if ("/::~".equals(message)) {
            return "[撇嘴]";
        } else if ("/:B-)".equals(message)) {
            return "[坏笑]";
        } else if ("/::L".equals(message)) {
            return "[流汗]";
        } else if ("/:handclap".equals(message)) {
            return "[鼓掌]";
        } else if ("/:coffee".equals(message)) {
            return "[咖啡]";
        } else if ("/:,@f".equals(message)) {
            return "[奋斗]";
        } else if ("[Hey]".equals(message)) {
            return "{Hey}";
        } else if ("[Facepalm]".equals(message)) {
            return "{Facepalm}";
        } else if ("[Smirk]".equals(message)) {
            return "{Smirk}";
        } else if ("[Smart]".equals(message)) {
            return "{Smart}";
        } else if ("[Concerned]".equals(message)) {
            return "{Concerned}";
        } else if ("[Yeah!]".equals(message)) {
            return "{Yeah!}";
        } else if ("[Packet]".equals(message)) {
            return "{Packet}";
        } else if ("[Chick]".equals(message)) {
            return "{Chick}";
        } else {
            if (message.length() != 1) {
                return null;
            }
            int code = message.charAt(0);
            if (code == 58389) {
                return "{58389}";
            } else if (code == 58380) {
                return "{58380}";
            } else if (code == 58386) {
                return "{58386}";
            } else if (code == 58377) {
                return "{58377}";
            } else if (code == 58381) {
                return "{58381}";
            } else if (code == 57607) {
                return "{57607}";
            } else if (code == 58371) {
                return "{58371}";
            } else if (code == 58382) {
                return "{58382}";
            } else if (code == 57627) {
                return "{57627}";
            } else if (code == 58397) {
                return "{58397}";
            } else if (code == 57676) {
                return "{57676}";
            } else if (code == 58130) {
                return "{58130}";
            } else if (code == 57618) {
                return "{57618}";
            } else {
                return null;
            }
        }
    }

    public static String token() {
        String appID = IceWxContext.getAppID();
        String appsecret = IceWxContext.getAppsecret();
        appID = appID.trim();
        appsecret = appsecret.trim();
        String tokenHashValue = SecureUtil.md5(appID + appsecret);
        return tokenHashValue;
    }

    public static Map<String, String> map(String url) {
        String[] arrray = url.split("&");
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < arrray.length; i++) {
            String[] pair = arrray[i].split("=");
            map.put(pair[0], pair[1]);
        }
        return map;
    }

    public static void scanPackage(String classPath) {
        File folder = new File(classPath);
        scnaPackage(folder, classPath);
    }

    private static void scnaPackage(File file, String classPath) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File fl : files) {
                scnaPackage(fl, classPath);
            }
        } else {
            String name = file.getName();
            if (name.endsWith(".class") && !name.contains("$")) {
                String absolutePath = file.getAbsolutePath();
                absolutePath = absolutePath.replace("\\", "/");
                String packagePath = StrUtil.removePrefix(absolutePath, classPath);
                String classFileName = packagePath.replace("/", ".");
                classFileName = StrUtil.removeSuffix(classFileName, ".class");
                initClassContext(classFileName);
            }
        }
    }

    private static void initClassContext(String classFileName) {
        @SuppressWarnings("rawtypes")
        Class clazz = null;
        try {
            clazz = Class.forName(classFileName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (clazz != null && !clazz.isInterface()) {
            ProcessorClassUtil.initClassContext(clazz);
        }
    }

    public static String serverWebName(String classPath) {
        int rightIndex = classPath.lastIndexOf("/WEB-INF/classes/");
        int leftIndex = classPath.lastIndexOf("/", rightIndex - 1);
        String projectName = null;
        if (leftIndex != -1 && rightIndex != -1) {
            projectName = classPath.substring(leftIndex + 1, rightIndex);
            return projectName;
        }
        return null;
    }

    public static String serverXml(String classPath) {
        String projectName = serverWebName(classPath);
        log.info("projectName = {}", projectName);
        classPath = StrUtil.removeSuffix(classPath, "/WEB-INF/classes/");
        classPath = StrUtil.removeSuffix(classPath, projectName);
        classPath = StrUtil.removeSuffix(classPath, "/");
        classPath = StrUtil.removeSuffix(classPath, "wtpwebapps");
        classPath = StrUtil.removeSuffix(classPath, "webapps");
        classPath += "conf/server.xml";
        return classPath;
    }

    public static String readPort(String serverXmlPath) {
        List<String> readUtf8Lines = FileUtil.readUtf8Lines(serverXmlPath);
        boolean isComment = false;
        boolean isConnector = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < readUtf8Lines.size(); i++) {
            String line = readUtf8Lines.get(i);
            line = line.trim();
            if (line.startsWith("<!--") && line.endsWith("-->")) {
                continue;
            }
            if (line.startsWith("<!--")) {
                isComment = true;
                continue;
            }
            if (line.endsWith("-->")) {
                isComment = false;
                continue;
            }
            if (!isComment) {
                if (line.contains(Const.PROTOCOL_HHTP) && line.startsWith("<Connector") && line.endsWith("/>")) {
                    String port = port(line);
                    return port;
                }
                if (line.startsWith("<Connector") && !line.endsWith("/>")) {
                    sb.append(line);
                    sb.append(" ");
                    isConnector = true;
                } else {
                    if (isConnector) {
                        sb.append(line);
                        if (line.endsWith("/>")) {
                            isConnector = false;
                            line = sb.toString();
                            if (line.contains(Const.PROTOCOL_HHTP) && line.startsWith("<Connector")
                                    && line.endsWith("/>")) {
                                String port = port(line);
                                return port;
                            }
                        } else {
                            sb.append(" ");
                        }
                    }
                }
            }
        }
        return null;
    }

    private static String port(String line) {
        line = line.trim();
        line = StrUtil.removePrefix(line, "<Connector");
        line = StrUtil.removeSuffix(line, "/>");
        line = line.trim();
        line = line.replaceAll("\\s*=\\s*", "=");
        String[] array = line.split("\\s+");
        for (int i = 0; i < array.length; i++) {
            String field = array[i];
            if (field != null && field.contains("port") && field.contains("=")) {
                String value = StrUtil.subAfter(field, "=", false);
                value = StrUtil.removePrefix(value, "\"");
                value = StrUtil.removeSuffix(value, "\"");
                return value;
            }
        }
        return null;
    }

    public static String srcName(String classPath) {
        String eclipseTag = "org.eclipse.wst.server.core";
        if (classPath.contains(eclipseTag)) {
            String path = StrUtil.subBefore(classPath, eclipseTag, false);
            path = StrUtil.removeSuffix(path, ".plugins/");
            path = StrUtil.removeSuffix(path, ".metadata/");
            String projectName = serverWebName(classPath);
            path += projectName + "/pom.xml";
            if (FileUtil.exist(path)) {
                return "src/main/resources";
            }
        }
        return "src";
    }

    public static boolean isJavaVersionError(String result) {
        if (StrUtil.isBlank(result)) {
            return false;
        }
        if (result.contains(Const.VERSION_ERROR) && result.contains(Const.VERSION_ERROR_JAVA8)) {
            return true;
        }
        return false;
    }

    public static void reply(String replyMessage) {
        if (replyMessage != null) {
            if ("".equals(replyMessage)) {
                MessageSender.sendEmptyMessage();
            } else {
                MessageSender.send(replyMessage);
            }
        }
    }

    public static String getUrl(StringBuffer requestURL, String queryString) {
        String url = requestURL.toString();
        if (StrUtil.isNotBlank(queryString)) {
            url += "?" + queryString;
        }
        return url;
    }
}
