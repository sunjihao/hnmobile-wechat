package com.hnmobile.wechat.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLUtil {

	////mobilememberserver/login.do
	public static boolean isLoginOrVerifyCodeUrl( String url ){
		Pattern p = Pattern.compile(".+?/(login|verifyCode)\\.do"); 
        Matcher m = p.matcher(url);
        //登录请求或者验证码请求，不做session登录判断
        return m.matches();
	}
	
	public static boolean isLogout( String url ){
		Pattern p = Pattern.compile(".+?/(logout)\\.do"); 
        Matcher m = p.matcher(url);
        //登录请求或者验证码请求，不做session登录判断
        return m.matches();
	}
	
	public static boolean isActionUrl( String url ){
		Pattern p = Pattern.compile(".+/.+?\\.do\\?{0,1}.*"); 
		Matcher m = p.matcher(url);
		return m.matches();
	}
	
	//http://10.20.92.24:8080/mobilememberserver/client/member_mgr/get_member_card_list.do
	public static boolean isAppUrl( String url ){
        Pattern p = Pattern.compile(".+/client/.+?\\.do\\?{0,1}.*"); 
        Matcher m = p.matcher(url);
        return m.matches();
    }
	
	//无需登录就可以访问的URL
	public static boolean isAppNoLoginUrl(String url) {
	    if (isUserAccountExists(url)||isRegister(url)||isPasswdReset(url)) {
	        return true;
	    }
	    return false;
	}
	
	public static boolean isUserAccountExists(String url){
        Pattern p = Pattern.compile(".+/client/user_mgr/is_user_exists.do"); //=[\\w]+?
        Matcher m = p.matcher(url);
        return m.matches();
    }
	
	public static boolean isRegister(String url){
        Pattern p = Pattern.compile(".+/client/user_mgr/register.do"); //=[\\w]+?
        Matcher m = p.matcher(url);
        return m.matches();
    }
	
	public static boolean isPasswdReset(String url){
        Pattern p = Pattern.compile(".+/client/user_mgr/reset_user_passwd.do"); //=[\\w]+?
        Matcher m = p.matcher(url);
        return m.matches();
    }
	
	public static void main(String[]args) {
	   String url = "http://10.20.92.24:8080/mobilememberserver/client/user_mgr/is_user_exists.do?userAccount=sunjihao&dsd";
	   System.out.println(isUserAccountExists(url));
	}
}
