package com.hnmobile.wechat.mvc.model;

import java.util.Date;

public class UserPasswdResetLogVO {
    
	private int resetLogId;
	private String userAccount;
	private String resetDeviceId;
	private String resetIp;
	private Date resetTime;
	private int resetErrorTime;
	private String resetResult;
	
    public int getResetLogId() {
        return resetLogId;
    }
    public void setResetLogId(int resetLogId) {
        this.resetLogId = resetLogId;
    }
    public String getUserAccount() {
        return userAccount;
    }
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
    public String getResetDeviceId() {
        return resetDeviceId;
    }
    public void setResetDeviceId(String resetDeviceId) {
        this.resetDeviceId = resetDeviceId;
    }
    public String getResetIp() {
        return resetIp;
    }
    public void setResetIp(String resetIp) {
        this.resetIp = resetIp;
    }
    public Date getResetTime() {
        return resetTime;
    }
    public void setResetTime(Date resetTime) {
        this.resetTime = resetTime;
    }
    public int getResetErrorTime() {
        return resetErrorTime;
    }
    public void setResetErrorTime(int resetErrorTime) {
        this.resetErrorTime = resetErrorTime;
    }
    public String getResetResult() {
        return resetResult;
    }
    public void setResetResult(String resetResult) {
        this.resetResult = resetResult;
    }
	
}
