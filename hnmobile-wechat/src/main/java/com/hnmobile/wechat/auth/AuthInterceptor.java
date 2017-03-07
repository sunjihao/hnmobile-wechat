package com.hnmobile.wechat.auth;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hnmobile.wechat.mvc.Constant;
import com.hnmobile.wechat.mvc.controller.UserMgrController;
import com.hnmobile.wechat.mvc.model.UserFuncVO;
import com.hnmobile.wechat.mvc.model.UserVO;
import com.hnmobile.wechat.mvc.service.LoginService;
import com.hnmobile.wechat.util.URLUtil;


public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	Logger log = Logger.getLogger(UserMgrController.class);
	
	private String autExceptionPage="/authen_exception.html";
    
	
	@Autowired
	private LoginService loginService;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
    	return true;
     }
    
    public boolean authen(String userAccount,List<UserFuncVO> funcList,String url,HttpServletRequest request,HttpServletResponse response) throws IOException{
    	
    	return true;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
}
