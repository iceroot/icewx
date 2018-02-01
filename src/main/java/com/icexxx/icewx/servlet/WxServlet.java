package com.icexxx.icewx.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icexxx.icewx.cont.Const;
import com.icexxx.icewx.msg.ClickEventProcessor;
import com.icexxx.icewx.msg.DefaultMessageProcessor;
import com.icexxx.icewx.msg.FileProcessor;
import com.icexxx.icewx.msg.ImageProcessor;
import com.icexxx.icewx.msg.InitProcessor;
import com.icexxx.icewx.msg.LinkProcessor;
import com.icexxx.icewx.msg.LocationEventProcessor;
import com.icexxx.icewx.msg.LocationProcessor;
import com.icexxx.icewx.msg.MessageSender;
import com.icexxx.icewx.msg.ScanEventProcessor;
import com.icexxx.icewx.msg.ShortvideoProcessor;
import com.icexxx.icewx.msg.SubscribeEventProcessor;
import com.icexxx.icewx.msg.TextProcessor;
import com.icexxx.icewx.msg.UnsubscribeEventProcessor;
import com.icexxx.icewx.msg.VideoProcessor;
import com.icexxx.icewx.msg.ViewEventProcessor;
import com.icexxx.icewx.msg.VoiceProcessor;
import com.icexxx.icewx.service.AccessTokenService;
import com.icexxx.icewx.service.IceServletContext;
import com.icexxx.icewx.service.IceSettingContext;
import com.icexxx.icewx.service.IceWxContext;
import com.icexxx.icewx.service.MessageContext;
import com.icexxx.icewx.service.MessageKeyContext;
import com.icexxx.icewx.service.ProcessorContext;
import com.icexxx.icewx.util.IceWxUtil;
import com.icexxx.icewx.util.IceXmlUtil;
import cn.hutool.cron.CronUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.setting.Setting;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;

/**
 * icewx &nbsp; 核心servlet
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class WxServlet extends HttpServlet {
    private static final long serialVersionUID = -4798004303070957299L;

    private static final Log log = LogFactory.get();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        String pathInfo = request.getPathInfo();
        String pathTranslated = request.getPathTranslated();
        String queryString = request.getQueryString();
        StringBuffer requestURL = request.getRequestURL();
        if (StrUtil.isNotBlank(signature)) {
            String token = IceWxContext.getToken();
            boolean sign = IceWxUtil.checkSign(token, timestamp, nonce, signature);
            if (sign) {
                response.getWriter().append(echostr);
            } else {
                response.getWriter().append("ERROR");
            }
        } else {
            if (pathInfo != null) {
                if (pathInfo.equals("/")) {
                    IceWxUtil.jumpJsp(response, pathTranslated);
                } else if (pathInfo.startsWith("/wx")) {
                    String token = request.getParameter("token");
                    String tokenHashValue = IceWxUtil.token();
                    String message = "ERROR";
                    if (StrUtil.isNotBlank(token) && token.equals(tokenHashValue)) {
                        String url = IceWxUtil.getUrl(requestURL, queryString);
                        log.info("url = " + url);
                        String suffix = StrUtil.removePrefix(pathInfo, "/wx/");
                        message = IceWxUtil.getMessageByUrl(suffix, queryString);
                    }
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().append(message);
                }

            } else {
                IceWxUtil.jumpJsp(response, pathTranslated);
            }
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        IceServletContext.setResponse(response);
        ServletInputStream inputStream = request.getInputStream();
        String read = IoUtil.read(inputStream, "UTF-8");
        if (read != null && !read.startsWith("<xml>")) {
            IceWxUtil.save(read);
            return;
        }
        Map<String, String> map = IceXmlUtil.read(read);
        String toUserName = map.get("ToUserName");
        String fromUserName = map.get("FromUserName");
        String createTime = map.get("CreateTime");
        String msgType = map.get("MsgType");
        MessageContext.setFromUserName(toUserName);
        MessageContext.setToUserName(fromUserName);
        MessageContext.setMsgType(msgType);
        if ("event".equals(msgType)) {// Event
            String event = map.get("Event");
            String replyMessage = null;
            if ("subscribe".equals(event)) {// 关注
                SubscribeEventProcessor subscribeEventMessageProcessor = ProcessorContext
                        .getSubscribeEventMessageProcessor();
                if (subscribeEventMessageProcessor == null) {
                    replyMessage = IceWxContext.getDefaultMessage();
                } else {
                    replyMessage = subscribeEventMessageProcessor.reply(fromUserName, createTime);
                }
            } else if ("unsubscribe".equals(event)) {// 取消关注
                UnsubscribeEventProcessor unsubscribeEventMessageProcessor = ProcessorContext
                        .getUnsubscribeEventMessageProcessor();
                if (unsubscribeEventMessageProcessor == null) {
                    log.info("取消关注" + fromUserName + ">" + IceWxUtil.date(createTime));
                } else {
                    replyMessage = unsubscribeEventMessageProcessor.reply(fromUserName, createTime);
                }
            } else if ("SCAN".equals(event)) {
                ScanEventProcessor scanEventMessageProcessor = ProcessorContext.getScanEventMessageProcessor();
                if (scanEventMessageProcessor == null) {
                    log.info("扫描二维码");
                } else {
                    String eventKey = map.get("EventKey");
                    String ticket = map.get("Ticket");
                    replyMessage = scanEventMessageProcessor.reply(fromUserName, createTime, eventKey, ticket);
                }
            } else if ("LOCATION".equals(event)) {
                LocationEventProcessor locationEventMessageProcessor = ProcessorContext
                        .getLocationEventMessageProcessor();
                String latitude = map.get("Latitude");
                String longitude = map.get("Longitude");
                String precision = map.get("Precision");
                if (locationEventMessageProcessor == null) {
                    log.info("上报地理位置,latitude=" + latitude + ",longitude=" + longitude + ",precision=" + precision);
                } else {
                    replyMessage = locationEventMessageProcessor.reply(fromUserName, createTime, latitude, longitude,
                            precision);
                }
            } else if ("CLICK".equals(event)) {
                ClickEventProcessor clickEventMessageProcessor = ProcessorContext.getClickEventMessageProcessor();
                String eventKey = map.get("EventKey");
                if (clickEventMessageProcessor == null) {
                    log.info("点击菜单");
                    replyMessage = MessageKeyContext.getKey(eventKey);
                } else {
                    replyMessage = clickEventMessageProcessor.reply(fromUserName, createTime, eventKey);
                }
            } else if ("VIEW".equals(event)) {
                ViewEventProcessor viewEventMessageProcessor = ProcessorContext.getViewEventMessageProcessor();
                String eventKey = map.get("EventKey");
                if (viewEventMessageProcessor == null) {
                    log.info("点击菜单跳转链接" + eventKey);
                } else {
                    replyMessage = viewEventMessageProcessor.reply(fromUserName, createTime, eventKey);
                }
            } else {
                log.info("其他事件" + event);
            }
            IceWxUtil.reply(replyMessage);
        } else {
            String msgId = map.get("MsgId");
            String lastMsgId = MessageContext.getLastMsgId();
            if (!msgId.equals(lastMsgId)) {
                MessageContext.setMsgId(msgId);
                if ("text".equals(msgType)) {
                    String content = map.get("Content");
                    String replyMessage = null;
                    TextProcessor defaultMessageProcessor = ProcessorContext.getDefaultMessageProcessor();
                    TextProcessor beforeMessageProcessor = ProcessorContext.getBeforeMessageProcessor();
                    TextProcessor afterMessageProcessor = ProcessorContext.getAfterMessageProcessor();
                    if (beforeMessageProcessor != null) {
                        replyMessage = beforeMessageProcessor.reply(content, fromUserName, createTime);
                        if (replyMessage != null) {
                            replyMessage = defaultMessageProcessor.reply(replyMessage, fromUserName, createTime);
                        }
                    } else {
                        replyMessage = defaultMessageProcessor.reply(content, fromUserName, createTime);
                    }
                    if (replyMessage != null && afterMessageProcessor != null) {
                        replyMessage = afterMessageProcessor.reply(replyMessage, fromUserName, createTime);
                    }
                    if (replyMessage != null) {
                        MessageSender.send(replyMessage);
                    } else {
                        MessageSender.sendEmptyMessage();
                    }
                } else if ("image".equals(msgType)) {// MediaId
                    String mediaId = map.get("MediaId");
                    String picUrl = map.get("PicUrl");
                    String replyMessage = "图片消息" + mediaId;
                    ImageProcessor imageMessageProcessor = ProcessorContext.getImageMessageProcessor();
                    if (imageMessageProcessor != null) {
                        replyMessage = imageMessageProcessor.reply(mediaId, fromUserName, createTime, picUrl);
                    }
                    IceWxUtil.reply(replyMessage);
                } else if ("voice".equals(msgType)) {// MediaId
                    String mediaId = map.get("MediaId");
                    String format = map.get("Format");
                    String recognition = map.get("Recongnition");
                    String replyMessage = "语音消息" + mediaId;
                    VoiceProcessor voiceMessageProcessor = ProcessorContext.getVoiceMessageProcessor();
                    if (voiceMessageProcessor != null) {
                        replyMessage = voiceMessageProcessor.reply(mediaId, fromUserName, createTime, format,
                                recognition);
                    }
                    IceWxUtil.reply(replyMessage);
                } else if ("video".equals(msgType)) {// MediaId
                    String mediaId = map.get("MediaId");
                    String thumbMediaId = map.get("ThumbMediaId");
                    String replyMessage = "视频消息" + mediaId;
                    VideoProcessor videoMessageProcessor = ProcessorContext.getVideoMessageProcessor();
                    if (videoMessageProcessor != null) {
                        replyMessage = videoMessageProcessor.reply(mediaId, fromUserName, createTime, thumbMediaId);
                    }
                    IceWxUtil.reply(replyMessage);
                } else if ("shortvideo".equals(msgType)) {// MediaId
                    String mediaId = map.get("MediaId");
                    String thumbMediaId = map.get("ThumbMediaId");
                    String replyMessage = "小视频消息" + mediaId;
                    ShortvideoProcessor shortvideoMessageProcessor = ProcessorContext.getShortvideoMessageProcessor();
                    if (shortvideoMessageProcessor != null) {
                        replyMessage = shortvideoMessageProcessor.reply(mediaId, fromUserName, createTime,
                                thumbMediaId);
                    }
                    IceWxUtil.reply(replyMessage);
                } else if ("location".equals(msgType)) {// MediaId
                    String locationX = map.get("Location_X");
                    String locationY = map.get("Location_Y");
                    String scale = map.get("Scale");
                    String label = map.get("Label");
                    String replyMessage = "地理位置" + locationX + ">" + locationY;
                    LocationProcessor locationMessageProcessor = ProcessorContext.getLocationMessageProcessor();
                    if (locationMessageProcessor != null) {
                        replyMessage = locationMessageProcessor.reply(fromUserName, createTime, locationX, locationY,
                                scale, label);
                    }
                    IceWxUtil.reply(replyMessage);
                } else if ("link".equals(msgType)) {
                    String title = map.get("Title");
                    String description = map.get("Description");
                    String url = map.get("Url");
                    String replyMessage = "链接消息" + url;
                    LinkProcessor linkMessageProcessor = ProcessorContext.getLinkMessageProcessor();
                    if (linkMessageProcessor != null) {
                        replyMessage = linkMessageProcessor.reply(fromUserName, createTime, title, description, url);
                    }
                    IceWxUtil.reply(replyMessage);
                } else if ("file".equals(msgType)) {
                    String title = map.get("Title");
                    String description = map.get("Description");
                    String fileKey = map.get("FileKey");
                    String fileMd5 = map.get("FileMd5");
                    String fileTotalLen = map.get("FileTotalLen");
                    String replyMessage = "文件消息" + title;
                    FileProcessor fileMessageProcessor = ProcessorContext.getFileMessageProcessor();
                    if (fileMessageProcessor != null) {
                        replyMessage = fileMessageProcessor.reply(fromUserName, createTime, title, description, fileKey,
                                fileMd5, fileTotalLen);
                    }
                    IceWxUtil.reply(replyMessage);
                } else {
                    MessageSender.send("其他消息" + msgType);
                }
            } else {
                MessageSender.sendEmptyMessage();
            }
            MessageContext.setLastMsgId(msgId);
        }
    }

    @Override
    public void init() throws ServletException {
        String oldAppID = IceWxContext.getAppID();
        if (StrUtil.isNotBlank(oldAppID)) {
            return;
        }
        String classPath = ClassUtil.getClassPath();
        String settingConfFile = classPath + "wx.properties";
        if (!FileUtil.exist(settingConfFile)) {
            String srcName = IceWxUtil.srcName(classPath);
            throw new RuntimeException(srcName + "目录下缺少配置文件wx.properties");
        }
        Setting setting = new Setting(settingConfFile);
        IceSettingContext.setSetting(setting);
        String appID = setting.getStr("appID");
        String appsecret = setting.getStr("appsecret");
        String url = setting.getStr("url");
        String token = setting.getStr("token");
        String messageSave = setting.getStr("messageSave");
        String defaultMessage = setting.getStr("defaultMessage");
        String beforeMessage = setting.getStr("beforeMessage");
        String afterMessage = setting.getStr("afterMessage");
        String imageMessage = setting.getStr("imageMessage");
        String voiceMessage = setting.getStr("voiceMessage");
        String videoMessage = setting.getStr("videoMessage");
        String shortvideoMessage = setting.getStr("shortvideoMessage");
        String locationMessage = setting.getStr("locationMessage");
        String linkMessage = setting.getStr("linkMessage");
        String fileMessage = setting.getStr("fileMessage");
        String clickEventMessage = setting.getStr("clickEventMessage");
        String viewEventMessage = setting.getStr("viewEventMessage");
        String locationEventMessage = setting.getStr("locationEventMessage");
        String scanEventMessage = setting.getStr("scanEventMessage");
        String subscribeEventMessage = setting.getStr("subscribeEventMessage");
        String unsubscribeEventMessage = setting.getStr("unsubscribeEventMessage");
        String init = setting.getStr("init");
        TextProcessor beforeMessageProcessor = IceWxUtil.newInstance(beforeMessage);
        TextProcessor afterMessageProcessor = IceWxUtil.newInstance(afterMessage);
        ImageProcessor imageMessageProcessor = IceWxUtil.newInstance(imageMessage);
        VoiceProcessor voiceMessageProcessor = IceWxUtil.newInstance(voiceMessage);
        VideoProcessor videoMessageProcessor = IceWxUtil.newInstance(videoMessage);
        ShortvideoProcessor shortvideoMessageProcessor = IceWxUtil.newInstance(shortvideoMessage);
        LocationProcessor locationMessageProcessor = IceWxUtil.newInstance(locationMessage);
        LinkProcessor linkMessageProcessor = IceWxUtil.newInstance(linkMessage);
        FileProcessor fileMessageProcessor = IceWxUtil.newInstance(fileMessage);
        ClickEventProcessor clickEventMessageProcessor = IceWxUtil.newInstance(clickEventMessage);
        ViewEventProcessor viewEventMessageProcessor = IceWxUtil.newInstance(viewEventMessage);
        LocationEventProcessor locationEventMessageProcessor = IceWxUtil.newInstance(locationEventMessage);
        ScanEventProcessor scanEventMessageProcessor = IceWxUtil.newInstance(scanEventMessage);
        SubscribeEventProcessor subscribeEventMessageProcessor = IceWxUtil.newInstance(subscribeEventMessage);
        UnsubscribeEventProcessor unsubscribeEventMessageProcessor = IceWxUtil.newInstance(unsubscribeEventMessage);
        InitProcessor initProcessor = IceWxUtil.newInstance(init);
        if (StrUtil.isBlank(appID)) {
            throw new RuntimeException("wx.properties配置文件的appID不能为空");
        }
        IceWxContext.setAppID(appID);
        if (StrUtil.isBlank(appsecret)) {
            throw new RuntimeException("wx.properties配置文件的appsecret不能为空");
        }
        IceWxContext.setAppsecret(appsecret);
        IceWxContext.setUrl(url);
        IceWxContext.setToken(token);
        IceWxContext.setMessageSave(messageSave);
        messageSave = IceWxUtil.getMessageSaveFile();
        if (defaultMessage == null) {
            defaultMessage = Const.DEFAULT_MESSAGE;
        }
        IceWxContext.setDefaultMessage(defaultMessage);
        if (beforeMessageProcessor != null) {
            ProcessorContext.setBeforeMessageProcessor(beforeMessageProcessor);
        }
        if (afterMessageProcessor != null) {
            ProcessorContext.setAfterMessageProcessor(afterMessageProcessor);
        }
        if (imageMessageProcessor != null) {
            ProcessorContext.setImageMessageProcessor(imageMessageProcessor);
        }
        if (voiceMessageProcessor != null) {
            ProcessorContext.setVoiceMessageProcessor(voiceMessageProcessor);
        }
        if (videoMessageProcessor != null) {
            ProcessorContext.setVideoMessageProcessor(videoMessageProcessor);
        }
        if (shortvideoMessageProcessor != null) {
            ProcessorContext.setShortvideoMessageProcessor(shortvideoMessageProcessor);
        }
        if (locationMessageProcessor != null) {
            ProcessorContext.setLocationMessageProcessor(locationMessageProcessor);
        }
        if (linkMessageProcessor != null) {
            ProcessorContext.setLinkMessageProcessor(linkMessageProcessor);
        }
        if (fileMessageProcessor != null) {
            ProcessorContext.setFileMessageProcessor(fileMessageProcessor);
        }
        if (clickEventMessageProcessor != null) {
            ProcessorContext.setClickEventMessageProcessor(clickEventMessageProcessor);
        }
        if (viewEventMessageProcessor != null) {
            ProcessorContext.setViewEventMessageProcessor(viewEventMessageProcessor);
        }
        if (locationEventMessageProcessor != null) {
            ProcessorContext.setLocationEventMessageProcessor(locationEventMessageProcessor);
        }
        if (scanEventMessageProcessor != null) {
            ProcessorContext.setScanEventMessageProcessor(scanEventMessageProcessor);
        }
        if (subscribeEventMessageProcessor != null) {
            ProcessorContext.setSubscribeEventMessageProcessor(subscribeEventMessageProcessor);
        }
        if (unsubscribeEventMessageProcessor != null) {
            ProcessorContext.setUnsubscribeEventMessageProcessor(unsubscribeEventMessageProcessor);
        }
        if (initProcessor != null) {
            ProcessorContext.setInitProcessor(initProcessor);
        }
        CronUtil.start();
        log.info("-----------------cron start-----------------");
        AccessTokenService.refresh();
        TextProcessor defaultMessageProcessor = new DefaultMessageProcessor();
        ProcessorContext.setDefaultMessageProcessor(defaultMessageProcessor);
        Map<String, String> map = IceWxUtil.read();
        MessageKeyContext.setMap(map);
        IceWxUtil.scanPackage(classPath);
        initProcessor = ProcessorContext.getInitProcessor();
        if (initProcessor != null) {
            initProcessor.init();
        }
    }
}
