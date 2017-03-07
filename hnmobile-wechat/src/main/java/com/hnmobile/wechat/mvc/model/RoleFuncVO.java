package com.hnmobile.wechat.mvc.model;

import java.util.Date;

public class RoleFuncVO {
	private int funcId;
	private String funcName;
	private String funcShowName;
	private String group;
	private Date updateTime;
	private String action="";
	private long funcType;//0单独分配 1 角色权限
	public int getFuncId() {
		return funcId;
	}
	public void setFuncId(int funcId) {
		this.funcId = funcId;
	}
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	public String getFuncShowName() {
		return funcShowName;
	}
	public void setFuncShowName(String funcShowName) {
		this.funcShowName = funcShowName;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public long getFuncType() {
		return funcType;
	}
	public void setFuncType(long funcType) {
		this.funcType = funcType;
	}
}
