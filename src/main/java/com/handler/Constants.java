package com.handler;

/**
 * author：RhineDream
 */
public abstract class Constants {

	/**
	 * 短信验证码密钥
	 */
	public static final String VERIFICATIONCODE_SECRET = "WWW.HOLLYSMART.COM_SMS_SECRET";
	
	/**
	 * Oauth认证信息密钥
	 */
	public static final String AUTHENTICATION_ID_SECRET = "WwW.HoLLYSmART.COM_eyJhbGciOiJIUzUxMiJ9_SeCrET";
	
    public static final String REQUEST_USERNAME = "username";
    public static final String REQUEST_PASSWORD = "password";

    public static final String REQUEST_USER_OAUTH_APPROVAL = "user_oauth_approval";

    public static final String OAUTH_LOGIN_VIEW = "oauth_login";
    public static final String OAUTH_APPROVAL_VIEW = "oauth_approval";

    private Constants() {
    }

    /****************************************** Exception Status *********************************************************/
    public static final int REQUEST_ACCESS_TOKEN_INVALID = 1011;	
    public static final int REQUEST_ACCESS_TOKEN_EXPIRED = 1012;
    public static final int REQUEST_REFRESH_TOKEN_INVALID = 1013;
    public static final int REQUEST_REFRESH_TOKEN_EXPIRED = 1014;
    
    public static final int REQUEST_DOES_NOT_HAVE_PERMISSION = 1015;
    
    
    /******************************************************************************************************************/
    
    
    /****************************************** Exception Message *********************************************************/
	public static final String REQUEST_MISSING_PARAM_EXCEPTION = "请求缺失参数！";
	public static final String REQUEST_URL_NOT_FOUND_EXCEPTION = "请求URL不存在！";
	public static final String REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION = "请求方式不支持！";
	public static final String REQUEST_RESOURCE_UNAUTHORIZED = "请求资源未授权，不允许访问！";
	public static final String REQUEST_METHOD_ARGUMENT_TYPE_MISMATCHEXCEPTION = "请求方法参数类型不匹配！";
	public static final String REQUEST_INTERFACE_EXCEEDS_THE_LIMIT_WITHIN_THE_SPECIFIED_TIME = "请求接口在规定时间内超出限定次数！";
	/******************************************************************************************************************/

	
	public static final String ROLE_OFFICE = "office";
	public static final String ROLE_USER = "user";
}
