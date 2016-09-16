package com.stephen.furniturerepair.app;

/**
 * Created by Stephen on 2016/4/3 0003.
 * Emial: 895745843@qq.com
 */
public class URL {
    public static final String URL_API = "http://www.91xuncai.com/kuaixiao_php/Kuaixiao/index.php/Home";


    /** 登录 */
    public static final String URL_LOGIN                        = "http://app.hashoping.com/sun/Admin/interface/login";
    /** 登出 */
    public static final String URL_LOGINOUT                     = "http://app.hashoping.com/sun/Admin/interface/logout";
    /** 登出 */
    public static final String URL_CHECK_LOGIN_STATE            = "http://app.hashoping.com/sun/Admin/interface/check_login";


    /**  注册接口 */
    public static final String URL_REGIST                       = URL_API + "/User/register";

    /** 获取验证码 */
    public static final String URL_GET_VERIFICATION             = URL_API + "/User/verifyCode";

    /** 修改密码 */
    public static final String URL_LOGIN_MODIFY_PASSWORD        = URL_API + "/User/changePassword";

}
