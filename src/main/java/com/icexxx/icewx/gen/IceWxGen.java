package com.icexxx.icewx.gen;

/**
 * icewx配置文件生成
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class IceWxGen {
    /**
     * 生成web.xml配置文件
     * 
     * @return 生成的web.xml配置文件
     */
    public static String gen() {
        String newLine = "\r\n";
        StringBuilder sb = new StringBuilder();
        sb.append("    <servlet>");
        sb.append(newLine);
        sb.append("        <servlet-name>iceWxServlet</servlet-name>");
        sb.append(newLine);
        sb.append("        <servlet-class>com.icexxx.icewx.servlet.WxServlet</servlet-class>");
        sb.append(newLine);
        sb.append("    </servlet>");
        sb.append(newLine);
        sb.append("    <servlet-mapping>");
        sb.append(newLine);
        sb.append("        <servlet-name>iceWxServlet</servlet-name>");
        sb.append(newLine);
        sb.append("        <url-pattern>/*</url-pattern>");
        sb.append(newLine);
        sb.append("    </servlet-mapping>");
        sb.append(newLine);
        return sb.toString();
    }
}
