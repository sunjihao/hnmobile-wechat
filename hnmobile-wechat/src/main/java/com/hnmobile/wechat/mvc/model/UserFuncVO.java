package com.hnmobile.wechat.mvc.model;

import java.util.Date;

public class UserFuncVO {
	private int funcId;
	private String funcName;
	private String funcShowName;
	private String funcUrl;
	private String action="";
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
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getFuncUrl() {
		return funcUrl;
	}
	public void setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl;
	}
}
