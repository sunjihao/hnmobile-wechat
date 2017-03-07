package com.hnmobile.wechat.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.hnmobile.wechat.dao.RoleDao;
import com.hnmobile.wechat.mvc.model.PageObject;
import com.hnmobile.wechat.mvc.model.RoleFuncVO;
import com.hnmobile.wechat.mvc.model.RoleVO;


public class RoleMgrService {

	@Autowired
	private RoleDao roleDao;

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public PageObject<RoleVO> queryRole(int pageNo, int pageSize,String roleId) {
		return roleDao.queryRole(pageNo,pageSize,roleId);
	}

	public PageObject<RoleFuncVO> queryRoleFunc(int pageNo, int pageSize,
			String roleId,String funcShowName,String funcName) {
		return roleDao.queryRoleFunc(pageNo,pageSize,roleId,funcShowName,funcName);
	}

	public PageObject<RoleFuncVO> queryRoleWithoutFunc(int pageNo,int pageSize, 
			String roleId,String funcShowName,String funcName) {
		return roleDao.queryRoleWithoutFunc(pageNo,pageSize,roleId,funcShowName,funcName);
	}

	public String editRole(String roleId,String roleName) {
		try{
			roleDao.editRole(roleId,roleName);
		}catch(Exception e){
			return e.getMessage();
		}
		return "Y";
	}

	public String addRole(String roleName) {
		try{
			roleDao.addRole(roleName);
		}catch(Exception e){
			return e.getMessage();
		}
		return "Y";
	}

	public String delRole(String roleId) {
		try{
			roleDao.delRole(roleId);
		}catch(Exception e){
			return e.getMessage();
		}
		return "Y";
	}

	public String delRoleFunc(String roleId, String funcId) {
		try{
			roleDao.delRoleFunc(roleId,funcId);
		}catch(Exception e){
			return e.getMessage();
		}
		return "Y";
	}

	public String addRoleFunc(String roleId, String funcId) {
		try{
			roleDao.addRoleFunc(roleId,funcId);
		}catch(Exception e){
			return e.getMessage();
		}
		return "Y";
	}

}
