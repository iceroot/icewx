package com.icexxx.icewx.util;

import com.icexxx.icewx.msg.ClickEventProcessor;
import com.icexxx.icewx.msg.FileProcessor;
import com.icexxx.icewx.msg.ImageProcessor;
import com.icexxx.icewx.msg.InitProcessor;
import com.icexxx.icewx.msg.LinkProcessor;
import com.icexxx.icewx.msg.LocationEventProcessor;
import com.icexxx.icewx.msg.LocationProcessor;
import com.icexxx.icewx.msg.ScanEventProcessor;
import com.icexxx.icewx.msg.ShortvideoProcessor;
import com.icexxx.icewx.msg.SubscribeEventProcessor;
import com.icexxx.icewx.msg.TextProcessor;
import com.icexxx.icewx.msg.UnsubscribeEventProcessor;
import com.icexxx.icewx.msg.VideoProcessor;
import com.icexxx.icewx.msg.ViewEventProcessor;
import com.icexxx.icewx.msg.VoiceProcessor;
import com.icexxx.icewx.service.IceSettingContext;
import com.icexxx.icewx.service.ProcessorContext;

/**
 * icewx &nbsp; 消息返回操作器注入工具类
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class ProcessorClassUtil {

    public static void initClassContext(Class<?> clazz) {
        String className = clazz.getName();
        if (TextProcessor.class.isAssignableFrom(clazz)) {
            String keyName = "beforeMessage";
            String beforeMessage = IceSettingContext.getSetting(keyName);
            if (beforeMessage == null) {
                TextProcessor beforeMessageProcessor = IceWxUtil.newInstance(className);
                if (beforeMessageProcessor != null) {
                    ProcessorContext.setBeforeMessageProcessor(beforeMessageProcessor);
                }
            }
        } else if (ImageProcessor.class.isAssignableFrom(clazz)) {
            String keyName = "imageMessage";
            String imageMessage = IceSettingContext.getSetting(keyName);
            if (imageMessage == null) {
                ImageProcessor imageMessageProcessor = IceWxUtil.newInstance(className);
                if (imageMessageProcessor != null) {
                    ProcessorContext.setImageMessageProcessor(imageMessageProcessor);
                }
            }
        } else if (LocationProcessor.class.isAssignableFrom(clazz)) {
            String keyName = "locationMessage";
            String locationMessage = IceSettingContext.getSetting(keyName);
            if (locationMessage == null) {
                LocationProcessor locationMessageProcessor = IceWxUtil.newInstance(className);
                if (locationMessageProcessor != null) {
                    ProcessorContext.setLocationMessageProcessor(locationMessageProcessor);
                }
            }
        } else if (VoiceProcessor.class.isAssignableFrom(clazz)) {
            String keyName = "voiceMessage";
            String voiceMessage = IceSettingContext.getSetting(keyName);
            if (voiceMessage == null) {
                VoiceProcessor voiceMessageProcessor = IceWxUtil.newInstance(className);
                if (voiceMessageProcessor != null) {
                    ProcessorContext.setVoiceMessageProcessor(voiceMessageProcessor);
                }
            }
        } else if (VideoProcessor.class.isAssignableFrom(clazz)) {
            String keyName = "videoMessage";
            String videoMessage = IceSettingContext.getSetting(keyName);
            if (videoMessage == null) {
                VideoProcessor videoMessageProcessor = IceWxUtil.newInstance(className);
                if (videoMessageProcessor != null) {
                    ProcessorContext.setVideoMessageProcessor(videoMessageProcessor);
                }
            }
        } else if (ShortvideoProcessor.class.isAssignableFrom(clazz)) {
            String keyName = "shortvideoMessage";
            String shortvideoMessage = IceSettingContext.getSetting(keyName);
            if (shortvideoMessage == null) {
                ShortvideoProcessor shortvideoMessageProcessor = IceWxUtil.newInstance(className);
                if (shortvideoMessageProcessor != null) {
                    ProcessorContext.setShortvideoMessageProcessor(shortvideoMessageProcessor);
                }
            }
        } else if (FileProcessor.class.isAssignableFrom(clazz)) {
            String keyName = "fileMessage";
            String fileMessage = IceSettingContext.getSetting(keyName);
            if (fileMessage == null) {
                FileProcessor fileMessageProcessor = IceWxUtil.newInstance(className);
                if (fileMessageProcessor != null) {
                    ProcessorContext.setFileMessageProcessor(fileMessageProcessor);
                }
            }
        } else if (LinkProcessor.class.isAssignableFrom(clazz)) {
            String keyName = "linkMessage";
            String linkMessage = IceSettingContext.getSetting(keyName);
            if (linkMessage == null) {
                LinkProcessor linkMessageProcessor = IceWxUtil.newInstance(className);
                if (linkMessageProcessor != null) {
                    ProcessorContext.setLinkMessageProcessor(linkMessageProcessor);
                }
            }
        } else if (ClickEventProcessor.class.isAssignableFrom(clazz)) {
            String keyName = "clickEventMessage";
            String clickEventMessage = IceSettingContext.getSetting(keyName);
            if (clickEventMessage == null) {
                ClickEventProcessor clickEventProcessor = IceWxUtil.newInstance(className);
                if (clickEventProcessor != null) {
                    ProcessorContext.setClickEventMessageProcessor(clickEventProcessor);
                }
            }
        } else if (LocationEventProcessor.class.isAssignableFrom(clazz)) {
            String keyName = "locationEventMessage";
            String locationEventMessage = IceSettingContext.getSetting(keyName);
            if (locationEventMessage == null) {
                LocationEventProcessor locationEventProcessor = IceWxUtil.newInstance(className);
                if (locationEventProcessor != null) {
                    ProcessorContext.setLocationEventMessageProcessor(locationEventProcessor);
                }
            }
        } else if (SubscribeEventProcessor.class.isAssignableFrom(clazz)) {
            String keyName = "subscribeEventMessage";
            String subscribeEventMessage = IceSettingContext.getSetting(keyName);
            if (subscribeEventMessage == null) {
                SubscribeEventProcessor subscribeEventProcessor = IceWxUtil.newInstance(className);
                if (subscribeEventProcessor != null) {
                    ProcessorContext.setSubscribeEventMessageProcessor(subscribeEventProcessor);
                }
            }
        } else if (UnsubscribeEventProcessor.class.isAssignableFrom(clazz)) {
            String keyName = "unsubscribeEventMessage";
            String unsubscribeEventMessage = IceSettingContext.getSetting(keyName);
            if (unsubscribeEventMessage == null) {
                UnsubscribeEventProcessor unsubscribeEventProcessor = IceWxUtil.newInstance(className);
                if (unsubscribeEventProcessor != null) {
                    ProcessorContext.setUnsubscribeEventMessageProcessor(unsubscribeEventProcessor);
                }
            }
        } else if (ViewEventProcessor.class.isAssignableFrom(clazz)) {
            String keyName = "viewEventMessage";
            String viewEventMessage = IceSettingContext.getSetting(keyName);
            if (viewEventMessage == null) {
                ViewEventProcessor viewEventProcessor = IceWxUtil.newInstance(className);
                if (viewEventProcessor != null) {
                    ProcessorContext.setViewEventMessageProcessor(viewEventProcessor);
                }
            }
        } else if (ScanEventProcessor.class.isAssignableFrom(clazz)) {
            String keyName = "scanEventMessage";
            String scanEventMessage = IceSettingContext.getSetting(keyName);
            if (scanEventMessage == null) {
                ScanEventProcessor scanEventProcessor = IceWxUtil.newInstance(className);
                if (scanEventProcessor != null) {
                    ProcessorContext.setScanEventMessageProcessor(scanEventProcessor);
                }
            }
        } else if (InitProcessor.class.isAssignableFrom(clazz)) {
            String keyName = "init";
            String init = IceSettingContext.getSetting(keyName);
            if (init == null) {
                InitProcessor initProcessor = IceWxUtil.newInstance(className);
                if (initProcessor != null) {
                    ProcessorContext.setInitProcessor(initProcessor);
                }
            }
        }
    }
}
