package com.hnmobile.wechat.mvc.model;

import java.util.Date;

public class UserLoginLogVO {
    
	private int loginLogId;
	private String userAccount;
	private String loginDeviceId;
	private String loginIp;
	private Date loginTime;
	private int loginErrorTime;
	private int lastLoginResult;
	
	public int getLoginLogId() {
		return loginLogId;
	}
	public void setLoginLogId(int loginLogId) {
		this.loginLogId = loginLogId;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public int getLoginErrorTime() {
		return loginErrorTime;
	}
	public void setLoginErrorTime(int loginErrorTime) {
		this.loginErrorTime = loginErrorTime;
	}
	public int getLastLoginResult() {
		return lastLoginResult;
	}
	public void setLastLoginResult(int lastLoginResult) {
		this.lastLoginResult = lastLoginResult;
	}
    public String getLoginDeviceId() {
        return loginDeviceId;
    }
    public void setLoginDeviceId(String loginDeviceId) {
        this.loginDeviceId = loginDeviceId;
    }
	
}
