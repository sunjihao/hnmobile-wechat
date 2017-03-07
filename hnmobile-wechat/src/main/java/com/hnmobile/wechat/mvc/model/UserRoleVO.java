package com.hnmobile.wechat.mvc.model;

import java.util.Date;

public class UserRoleVO {
	
	private int roleId;
	private String roleName;
	private String action="";
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
}
