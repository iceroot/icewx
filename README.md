# icewx
微信公众号平台框架(WeChat public number platform framework)

icewx微信公众号平台框架

icewx是一个微信公众号平台框架.该框架以简单易学作为基本设计原则.
icewx将与开发者不太相关的逻辑进行了尽可能的封装.
开发者需要配置web.xml中的iceWxServlet.
在资源路径下加入wx.properties配置文件.
通过重写TextProcessor接口,开发者可以重写消息的应答逻辑.

icewx is a WeChat public platform platform that uses easy to learn as a basic design principle.
Icewx encapsulates as much logic as possible to developers.
The developer needs to configure iceWxServlet in web.xml.
Add wx.properties configuration file in the resource path.
By rewriting the TextProcessor interface, developers can override the message's reply logic.
