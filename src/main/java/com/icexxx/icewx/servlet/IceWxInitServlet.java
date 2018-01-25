package com.icexxx.icewx.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.icexxx.icewx.cont.Const;
import com.icexxx.icewx.util.IceWxUtil;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

/**
 * icewx &nbsp; 初始启动servlet
 * 
 * @author IceWater
 * @version 1.0.0
 */
@WebServlet(urlPatterns = "/_iceWxUrl_", loadOnStartup = 1)
public class IceWxInitServlet extends HttpServlet {
    private static final long serialVersionUID = -9192597845357416448L;
    private static final Log log = LogFactory.get();

    static {
        log.info("----IceWxInitServlet start------");
        String classPath = ClassUtil.getClassPath();
        String serverXmlPath = IceWxUtil.serverXml(classPath);
        String webName = IceWxUtil.serverWebName(classPath);
        String port = "80";
        if (FileUtil.exist(serverXmlPath)) {
            port = IceWxUtil.readPort(serverXmlPath);
        }
        final String ip = NetUtil.getLocalhostStr();
        String pageUrl = "http://" + ip + ":" + port;
        if (!Const.ROOT.equals(webName)) {
            pageUrl += "/" + webName;
        }
        final String urlThread = pageUrl;
        final String portThread = port;
        new Thread() {
            @Override
            public void run() {
                String result = null;
                try {
                    result = HttpUtil.get(urlThread);
                    if (result != null) {
                        log.info("url=" + urlThread);
                        log.info(StrUtil.subPre(result, 100));
                        if (IceWxUtil.isJavaVersionError(result)) {
                            throw new RuntimeException("代码java版本不一致,建议使用jdk 1.7重新编译war文件.");
                        }
                    }
                } catch (HttpException e) {
                    ThreadUtil.sleep(30000);
                    String urlThread = "http://" + ip + ":" + portThread;
                    try {
                        result = HttpUtil.get(urlThread);
                    } catch (HttpException e1) {
                        urlThread = "https://" + ip + ":" + portThread;
                        try {
                            result = HttpUtil.get(urlThread);
                        } catch (HttpException e2) {
                            urlThread = "http://" + ip + ":" + "8080";
                            try {
                                result = HttpUtil.get(urlThread);
                            } catch (HttpException e3) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }.start();
    }
}