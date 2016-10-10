package com.stephen.furniturerepair.app;

/**
 * Created by Stephen on 2016/4/3 0003.
 * Emial: 895745843@qq.com
 */
public class URL {
    public static final String URL_API = "http://hashoping.com";


    /** 登录 */
    public static final String URL_LOGIN                        = URL_API +"/admin/interface/login";

    /** 退出 */
    public static final String URL_LOGINOUT                     = URL_API +"/admin/interface/logout";

    /** 检测登陆 */
    public static final String URL_CHECK_LOGIN_STATE            = URL_API +"/admin/interface/check_login";

    /** 发送验证码到手机号 */
    public static final String URL_SEND_MOBILE_SMS              = URL_API +"/admin/interface/send_mobile_sms";

    /** 验证手机短信验证码 */
    public static final String URL_CHECK_SMS                    = URL_API +"/admin/interface/check_sms";

    /** 注册 */
    public static final String URL_REGISTER                     = URL_API +"/admin/interface/register";

    /** 信息发布 */
    public static final String URL_PUBLISH                      = URL_API +"/admin/interface/publish";

    /** 修改个人信息 */
    public static final String URL_UPDATE_MEMBER_INFO           = URL_API +"/admin/interface/update_member_info";

    /** 修改个人头像 */
    public static final String URL_UPDATE_AVATAR                = URL_API +"/sun/Admin/interface/update_avatar";

    /** 忘记密码 */
    public static final String URL_FORGET_PASSWORD              = URL_API +"/Admin/interface/forget_password";

    /** 修改密码 */
    public static final String URL_UPDATE_PASSWORD              = URL_API +"/Admin/interface/update_password";



    /**************************************************************** 请勿格式化 *************************************************************************/

}
