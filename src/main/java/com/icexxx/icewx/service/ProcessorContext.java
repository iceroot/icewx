package com.icexxx.icewx.service;

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

/**
 * icewx &nbsp; 消息回调上下文
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class ProcessorContext {
    private static ImageProcessor imageMessageProcessor;
    private static VoiceProcessor voiceMessageProcessor;
    private static VideoProcessor videoMessageProcessor;
    private static ShortvideoProcessor shortvideoMessageProcessor;
    private static LocationProcessor locationMessageProcessor;
    private static LinkProcessor linkMessageProcessor;
    private static FileProcessor fileMessageProcessor;
    private static TextProcessor defaultMessageProcessor;
    private static TextProcessor beforeMessageProcessor;
    private static TextProcessor afterMessageProcessor;
    private static ClickEventProcessor clickEventMessageProcessor;
    private static ViewEventProcessor viewEventMessageProcessor;
    private static LocationEventProcessor locationEventMessageProcessor;
    private static ScanEventProcessor scanEventMessageProcessor;
    private static SubscribeEventProcessor subscribeEventMessageProcessor;
    private static UnsubscribeEventProcessor unsubscribeEventMessageProcessor;
    private static InitProcessor initProcessor;

    public static ImageProcessor getImageMessageProcessor() {
        return imageMessageProcessor;
    }

    public static void setImageMessageProcessor(ImageProcessor imageMessageProcessor) {
        ProcessorContext.imageMessageProcessor = imageMessageProcessor;
    }

    public static VoiceProcessor getVoiceMessageProcessor() {
        return voiceMessageProcessor;
    }

    public static void setVoiceMessageProcessor(VoiceProcessor voiceMessageProcessor) {
        ProcessorContext.voiceMessageProcessor = voiceMessageProcessor;
    }

    public static VideoProcessor getVideoMessageProcessor() {
        return videoMessageProcessor;
    }

    public static void setVideoMessageProcessor(VideoProcessor videoMessageProcessor) {
        ProcessorContext.videoMessageProcessor = videoMessageProcessor;
    }

    public static ShortvideoProcessor getShortvideoMessageProcessor() {
        return shortvideoMessageProcessor;
    }

    public static void setShortvideoMessageProcessor(ShortvideoProcessor shortvideoMessageProcessor) {
        ProcessorContext.shortvideoMessageProcessor = shortvideoMessageProcessor;
    }

    public static LocationProcessor getLocationMessageProcessor() {
        return locationMessageProcessor;
    }

    public static void setLocationMessageProcessor(LocationProcessor locationMessageProcessor) {
        ProcessorContext.locationMessageProcessor = locationMessageProcessor;
    }

    public static LinkProcessor getLinkMessageProcessor() {
        return linkMessageProcessor;
    }

    public static void setLinkMessageProcessor(LinkProcessor linkMessageProcessor) {
        ProcessorContext.linkMessageProcessor = linkMessageProcessor;
    }

    public static TextProcessor getDefaultMessageProcessor() {
        return defaultMessageProcessor;
    }

    public static void setDefaultMessageProcessor(TextProcessor defaultMessageProcessor) {
        ProcessorContext.defaultMessageProcessor = defaultMessageProcessor;
    }

    public static TextProcessor getBeforeMessageProcessor() {
        return beforeMessageProcessor;
    }

    public static void setBeforeMessageProcessor(TextProcessor beforeMessageProcessor) {
        ProcessorContext.beforeMessageProcessor = beforeMessageProcessor;
    }

    public static TextProcessor getAfterMessageProcessor() {
        return afterMessageProcessor;
    }

    public static void setAfterMessageProcessor(TextProcessor afterMessageProcessor) {
        ProcessorContext.afterMessageProcessor = afterMessageProcessor;
    }

    public static FileProcessor getFileMessageProcessor() {
        return fileMessageProcessor;
    }

    public static void setFileMessageProcessor(FileProcessor fileMessageProcessor) {
        ProcessorContext.fileMessageProcessor = fileMessageProcessor;
    }

    public static ClickEventProcessor getClickEventMessageProcessor() {
        return clickEventMessageProcessor;
    }

    public static void setClickEventMessageProcessor(ClickEventProcessor clickEventMessageProcessor) {
        ProcessorContext.clickEventMessageProcessor = clickEventMessageProcessor;
    }

    public static ViewEventProcessor getViewEventMessageProcessor() {
        return viewEventMessageProcessor;
    }

    public static void setViewEventMessageProcessor(ViewEventProcessor viewEventMessageProcessor) {
        ProcessorContext.viewEventMessageProcessor = viewEventMessageProcessor;
    }

    public static LocationEventProcessor getLocationEventMessageProcessor() {
        return locationEventMessageProcessor;
    }

    public static void setLocationEventMessageProcessor(LocationEventProcessor locationEventMessageProcessor) {
        ProcessorContext.locationEventMessageProcessor = locationEventMessageProcessor;
    }

    public static ScanEventProcessor getScanEventMessageProcessor() {
        return scanEventMessageProcessor;
    }

    public static void setScanEventMessageProcessor(ScanEventProcessor scanEventMessageProcessor) {
        ProcessorContext.scanEventMessageProcessor = scanEventMessageProcessor;
    }

    public static SubscribeEventProcessor getSubscribeEventMessageProcessor() {
        return subscribeEventMessageProcessor;
    }

    public static void setSubscribeEventMessageProcessor(SubscribeEventProcessor subscribeEventMessageProcessor) {
        ProcessorContext.subscribeEventMessageProcessor = subscribeEventMessageProcessor;
    }

    public static UnsubscribeEventProcessor getUnsubscribeEventMessageProcessor() {
        return unsubscribeEventMessageProcessor;
    }

    public static void setUnsubscribeEventMessageProcessor(UnsubscribeEventProcessor unsubscribeEventMessageProcessor) {
        ProcessorContext.unsubscribeEventMessageProcessor = unsubscribeEventMessageProcessor;
    }

    public static InitProcessor getInitProcessor() {
        return initProcessor;
    }

    public static void setInitProcessor(InitProcessor initProcessor) {
        ProcessorContext.initProcessor = initProcessor;
    }
}
