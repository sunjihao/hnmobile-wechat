package com.hnmobile.wechat.mvc.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnmobile.wechat.dao.LoginDao;
import com.hnmobile.wechat.mvc.model.UserLoginLogVO;
import com.hnmobile.wechat.mvc.model.UserPasswdResetLogVO;
import com.hnmobile.wechat.mvc.model.UserVO;
import com.hnmobile.wechat.util.MD5Util;


public class LoginService {

	@Autowired
	private LoginDao loginDao;
	
	private static LoginService loginService = new LoginService();
	
	/**
	 * 根据用户名，密码查找用户，查找到返回VO，否则返回null
	 * @param userVO 从数据库里面查出来的VO
	 * @param 用户输入的userName
	 * @return
	 */
	public UserLoginLogVO validateLoginUser( UserVO userVO,String passwd,String loginIp,String deviceId ){
		passwd = MD5Util.GetMD5Code(passwd) ;
		UserLoginLogVO userLoginLogVO=loginDao.getLoginLog(userVO.getUserAccount());
		if(userLoginLogVO==null) {
            userLoginLogVO = new UserLoginLogVO();
        }
		if( !userVO.getPasswd().equals(passwd) ){//登录验证没有通过，记录登录日志，记录登录失败次数
			userLoginLogVO.setLastLoginResult(-1);
			int loginErrorTime = userLoginLogVO.getLoginErrorTime()+1;
			userLoginLogVO.setLoginErrorTime(loginErrorTime);
			loginDao.inserLoginLog(userLoginLogVO);
		}else if(userVO.getPasswd().equals(passwd)){//登录验证通过，记录登录日志，登录失败次数清零。
		    userLoginLogVO.setLastLoginResult(1);
		    userLoginLogVO.setLoginErrorTime(0);
		}
		userLoginLogVO.setUserAccount(userVO.getUserAccount());
        userLoginLogVO.setLoginIp(loginIp);
        userLoginLogVO.setLoginTime(new Date());
        userLoginLogVO.setLoginDeviceId(deviceId);
		loginDao.inserLoginLog(userLoginLogVO);
		return userLoginLogVO;
	}
	
	public void insertResetPasswdLog( UserPasswdResetLogVO userPasswdResetLogVO ){
	    loginDao.insertResetPasswdLog(userPasswdResetLogVO);
    }
	
	public UserLoginLogVO getLoginLog( String userAccount ){
        return loginDao.getLoginLog(userAccount);
    }
	
	public UserPasswdResetLogVO getResetPasswdLog( String userAccount ){
        return loginDao.getResetPasswdLog(userAccount);
    }
	
	public void updateLoginInfo( UserVO uvo ){
		uvo.setUpdateTime( new Date() );
	}
	
	public UserVO lockUser( UserVO userVO ){
		userVO.setLock("1");
		loginDao.updateUser(userVO);
		return userVO;
	}
	
	public UserVO getUser( String userAccount ){
		return loginDao.getUserByName(userAccount);
	}
	
	public void updateLoginUser(){
		
	}

	public LoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	
	public static LoginService createInstance() {
        return loginService;
    }
	
}
