package com.hnmobile.wechat.mvc.model;

public class RoleVO {
	
	private int roleId;
	private String roleName;
	private String action="<a href='#' name='edit' class='edit'>[修改]</a><a href='#' name='del' class='del'>[删除]</a><a href='#' name='editRoleFunc' class='editRoleFunc'>[修改权限]</a>";
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
