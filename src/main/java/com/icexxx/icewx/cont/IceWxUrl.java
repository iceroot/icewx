package com.icexxx.icewx.cont;

/**
 * icewx 微信框架
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class IceWxUrl {
    public static final String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";// GET
    public static final String GET_CALLBACK_IP = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN";// GET
    public static final String USER_GET_LIST = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";// GET
    public static final String MENU_GET_LIST = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";// GET
    public static final String MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";// POST
    public static final String MENU_DELETE = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";// GET
    public static final String USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";// GET
    public static final String USER_INFO_BATCH = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";// POST
    public static final String USER_REMARK = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=ACCESS_TOKEN";// POST
    public static final String MASS_SENDALL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";// POST 根据标签进行群发
    public static final String MASS_SEND = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";// POST 根据OpenID列表群发

    /**
     * 个性化菜单
     */
    public static final String MENU_ADDCONDITIONAL = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=ACCESS_TOKEN"; //    POST 创建个性化菜单
    public static final String MENU_DELCONDITIONAL = "https://api.weixin.qq.com/cgi-bin/menu/delconditional?access_token=ACCESS_TOKEN"; //    POST 删除个性化菜单
    public static final String MENU_TRYMATCH = "https://api.weixin.qq.com/cgi-bin/menu/trymatch?access_token=ACCESS_TOKEN"; //    POST 测试个性化菜单匹配结果
    public static final String GET_CURRENT_SELFMENU_INFO = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=ACCESS_TOKEN"; // GET 获取自定义菜单配置
    /**
     * 客服
     */
    public static final String KFACCOUNT_ADD = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN"; // POST 添加客服帐号
    public static final String KFACCOUNT_UPDATE = "https://api.weixin.qq.com/customservice/kfaccount/update?access_token=ACCESS_TOKEN"; // POST 修改客服帐号
    public static final String KFACCOUNT_DEL = "https://api.weixin.qq.com/customservice/kfaccount/del?access_token=ACCESS_TOKEN"; // GET 删除客服帐号
    public static final String KFACCOUNT_UPLOADHEADIMG = "http://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token=ACCESS_TOKEN&kf_account=KFACCOUNT"; // POST/FORM 设置客服帐号的头像
    public static final String KFACCOUNT_GETKFLIST = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=ACCESS_TOKEN"; // GET 获取所有客服账号
    public static final String KFACCOUNT_SEND = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN"; // POST 客服接口-发消息
    public static final String KFACCOUNT_TYPING = "https://api.weixin.qq.com/cgi-bin/message/custom/typing?access_token=ACCESS_TOKEN"; // POST 客服输入状态
    /**
     * 群发消息
     */
    public static final String UPLOADIMG = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN"; //POST 上传图文消息内的图片
    public static final String UPLOADNEWS = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN"; //POST 上传图文消息素材
    public static final String UPLOADVIDEO = "https://api.weixin.qq.com/cgi-bin/media/uploadvideo?access_token=ACCESS_TOKEN"; //POST 上传视频素材
    public static final String MASS_DELETE = "https://api.weixin.qq.com/cgi-bin/message/mass/delete?access_token=ACCESS_TOKEN"; //POST 删除群发
    public static final String MASS_PREVIEW = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN"; //POST 预览接口
    public static final String MASS_GET = "https://api.weixin.qq.com/cgi-bin/message/mass/get?access_token=ACCESS_TOKEN"; //POST 查询群发消息发送状态
    public static final String MASS_SPEED = "https://api.weixin.qq.com/cgi-bin/message/mass/speed/get?access_token=ACCESS_TOKEN"; //POST 控制群发速度
    public static final String MASS_SPEED_SET = "https://api.weixin.qq.com/cgi-bin/message/mass/speed/set?access_token=ACCESS_TOKEN"; //POST 控制群发速度
    /**
     * 模板消息
     */
    public static final String TEMPLATE_SET_INDUSTRY = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN"; //POST 设置所属行业
    public static final String TEMPLATE_GET_INDUSTRY = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN"; //GET 获取设置的行业信息
    public static final String TEMPLATE_ADD_TEMPLATE = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN"; //POST 获得模板ID
    public static final String TEMPLATE_GET_LIST = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN"; //GET 获取模板列表
    public static final String TEMPLATE_DEL = "https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=ACCESS_TOKEN"; //POST 删除模板
    public static final String TEMPLATE_SEND = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN"; //POST 发送模板消息
    /**
     * 一次性订阅消息
     */
    public static final String TEMPLATE_SUBSCRIBE = "https://api.weixin.qq.com/cgi-bin/message/template/subscribe?access_token=ACCESS_TOKEN"; // POST 通过API推送订阅模板消息给到授权微信用户
    /**
     * 获取公众号的自动回复规则
     */
    public static final String AUTOREPLY_INFO ="https://api.weixin.qq.com/cgi-bin/get_current_autoreply_info?access_token=ACCESS_TOKEN"; // GET 获取公众号的自动回复规则
    /**
     * 微信网页授权
     */
    public static final String GRANT ="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect"; // 用户授权
    public static final String GRANT_ACCESS_TOKEN ="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code"; // 通过code换取网页授权access_token
    public static final String GRANT_REFRESH_ACCESS_TOKEN ="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN"; // 刷新access_token
    public static final String SNS_USERINFO ="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN"; // GET 拉取用户信息
    public static final String SNS_CHECK ="https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID"; // GET 检验授权凭证
    /**
     * 微信卡券
     */
    public static final String CARD_GETTICKET ="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=wx_card"; //GET 获取api_ticket
    /**
     * 素材管理
     */
    public static final String MEDIA_UPLOAD ="https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";// POST/FORM 新增临时素材
    public static final String MEDIA_GET ="https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";// GET 获取临时素材
    public static final String MEDIA_HIGH_DEFINITION_VOICE ="https://api.weixin.qq.com/cgi-bin/media/get/jssdk?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";// GET 高清语音素材获取接口
    public static final String MATERIAL_ADD_NEWS ="https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";// POST 新增永久图文素材
    public static final String MATERIAL_ADD ="https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";// POST 新增其他类型永久素材
    public static final String MATERIAL_GET ="https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";// POST 获取永久素材
    public static final String MATERIAL_DEL ="https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=ACCESS_TOKEN";// POST 删除永久素材
    public static final String MATERIAL_UPDATE ="https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=ACCESS_TOKEN";// POST 修改永久图文素材
    public static final String MATERIAL_COUNT ="https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=ACCESS_TOKEN";// GET 获取素材总数
    public static final String MATERIAL_LIST ="https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";// POST 获取素材列表
    /**
     * 用户标签管理
     */
    public static final String TAGS_CREATE ="https://api.weixin.qq.com/cgi-bin/tags/create?access_token=ACCESS_TOKEN";// POST 创建标签
    public static final String TAGS_GET ="https://api.weixin.qq.com/cgi-bin/tags/get?access_token=ACCESS_TOKEN";// GET 获取公众号已创建的标签
    public static final String TAGS_UPDATE ="https://api.weixin.qq.com/cgi-bin/tags/update?access_token=ACCESS_TOKEN";// POST 编辑标签
    public static final String TAGS_DELETE ="https://api.weixin.qq.com/cgi-bin/tags/delete?access_token=ACCESS_TOKEN";// POST 删除标签
    public static final String TAGS_USERGET ="https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=ACCESS_TOKEN";// GET 获取标签下粉丝列表
    public static final String TAGS_BATCHTAGGING ="https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=ACCESS_TOKEN";// POST 批量为用户打标签
    public static final String TAGS_BATCHUNTAGGING ="https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=ACCESS_TOKEN";// POST 批量为用户取消标签
    public static final String TAGS_GETIDLIST ="https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token=ACCESS_TOKEN";// POST 获取用户身上的标签列表
    /**
     * 用户相关
     */
    public static final String TAGS_GETBLACKLIST ="https://api.weixin.qq.com/cgi-bin/tags/members/getblacklist?access_token=ACCESS_TOKEN"; // POST 获取公众号的黑名单列表
    public static final String TAGS_BATCHBLACKLIST ="https://api.weixin.qq.com/cgi-bin/tags/members/batchblacklist?access_token=ACCESS_TOKEN"; // POST 拉黑用户
    public static final String TAGS_BATCHUNBLACKLIST ="https://api.weixin.qq.com/cgi-bin/tags/members/batchunblacklist?access_token=ACCESS_TOKEN"; // POST 取消拉黑用户
    /**
     * 二维码
     */
    public static final String QRCODE_CREATE ="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN"; // POST 创建二维码ticket
    public static final String QRCODE_SHOWQRCODE ="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET"; // GET 通过ticket换取二维码
    /**
     * 长链接转短链接接口
     */
    public static final String SHORTURL ="https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN"; // POST 将一条长链接转成短链接
}
