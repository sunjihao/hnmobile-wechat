package com.hnmobile.wechat.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hnmobile.wechat.dao.UserDao;
import com.hnmobile.wechat.mvc.Constant;
import com.hnmobile.wechat.mvc.model.PageObject;
import com.hnmobile.wechat.mvc.model.PageVO;
import com.hnmobile.wechat.mvc.model.UserFuncVO;
import com.hnmobile.wechat.mvc.model.UserListVO;
import com.hnmobile.wechat.mvc.model.UserRoleVO;
import com.hnmobile.wechat.mvc.model.UserVO;
import com.hnmobile.wechat.util.MD5Util;

@Transactional
public class UserMgrService {

	@Autowired
	private UserDao userDao;
	
	public PageVO<UserListVO> getUserList( int pageNo,int pageSize ){
		return userDao.getUserList( pageNo,pageSize );
	}
	
	public PageVO<UserListVO> queryUser( int pageNo,int pageSize,String userAccount ){
		return userDao.queryUser( pageNo,pageSize,userAccount );
	}
	
	public PageObject<UserFuncVO> queryUserFunc(int pageNo, int pageSize,
			String userAccount,String funcShowName,String funcName,String isMenu) {
		return userDao.queryUserFunc( pageNo,pageSize,userAccount,funcShowName,funcName,isMenu );
	}
	
	public PageObject<UserRoleVO> queryUserRole(int pageNo, int pageSize,
			String userAccount) {
		return userDao.queryUserRole(pageNo,pageSize,userAccount);
	}
	
	public PageObject<UserFuncVO> queryUserWithoutFunc(int pageNo, int pageSize,
			String userAccount,String funcShowName,String funcName) {
		return userDao.queryUserWithoutFunc( pageNo,pageSize,userAccount,funcShowName,funcName );
	}
	
	public PageObject<UserRoleVO> queryUserWithoutRole(int pageNo,
			int pageSize, String userAccount) {
		return userDao.queryUserWithoutRole( pageNo,pageSize,userAccount);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateUserInfo(String userAccount,String registerTime,String tel,String birthday,
	                           String cname,String sex,String birthdayType){
		userDao.updateUserInfo(userAccount,registerTime,tel,birthday,cname,sex,birthdayType);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateUserPasswd(String userAccount,String passwd){
	    passwd = MD5Util.GetMD5Code(passwd);
        userDao.updateUserPasswd(userAccount,passwd);
    }
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateUserInfo(String userAccount,String roleId,String lock,String registerTime){
        userDao.updateUserInfo(userAccount,roleId,lock,registerTime);
    }
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateUserLock(String userAccount,String lock){
        userDao.updateUserLock(userAccount,lock);
    }
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addUserFunc(int funcId, String userAccount, String actionUser) {
		userDao.addUserFunc(funcId,userAccount,actionUser);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delUserFunc(int funcId, String userAccount, String actionUser) {
		userDao.delUserFunc(funcId,userAccount,actionUser);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delUserRole(int roleId, String userAccount, String actionUser) {
		userDao.delUserRole(roleId,userAccount,actionUser);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addUserRole(int roleId, String userAccount, String actionUser) {
		userDao.addUserRole(roleId,userAccount,actionUser);
	}
	
	public boolean isUserAccountExists(String memberAccount){
		return userDao.isUserAccountExists(memberAccount);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String addUserInfo(String userAccount, int roleId, String lock,String tel
	                        ,String birthday,String passwd,String birthdayType,String sex) {
	    if (StringUtils.isEmpty(passwd)) {
            passwd = Constant.defaultMD5Passwd;
        } else {
            passwd = MD5Util.GetMD5Code(passwd);
        }
		userDao.addUserInfo(userAccount,roleId,lock,tel,birthday,passwd,birthdayType,sex);
		return passwd;
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delUserInfo(String userAccount) {
		userDao.logicDelUserInfo(userAccount);
	}
	
	public UserVO getUser(String userAccount,String birthday,String birthdayType,String sex,String tel){
		return userDao.getUser(userAccount,birthday,birthdayType,sex,tel);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
