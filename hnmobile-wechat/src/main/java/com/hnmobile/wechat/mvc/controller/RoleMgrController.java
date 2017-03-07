package com.hnmobile.wechat.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnmobile.wechat.mvc.model.PageObject;
import com.hnmobile.wechat.mvc.model.RoleFuncVO;
import com.hnmobile.wechat.mvc.model.RoleVO;
import com.hnmobile.wechat.mvc.model.UserListVO;
import com.hnmobile.wechat.mvc.service.RoleMgrService;

import flexjson.JSONSerializer;

@Controller
public class RoleMgrController extends BaseController<UserListVO>{
	
	@Autowired
	private RoleMgrService roleMgrService;
	
	@RequestMapping("/role_mgr/init")
	public String initRoleContext(HttpServletRequest request,Model model) {
		super.initContext(request, model);
		return "role_manager/role";
	}
	
	@RequestMapping("/role_mgr/role_func/init")
	public String initRoleFuncContext(HttpServletRequest request,Model model) {
		super.initContext(request, model);
		String roleId = request.getParameter("roleId");
		model.addAttribute("roleId", roleId);
		return "role_manager/role_func";
	}
	
	@RequestMapping( value="/role_mgr/list", produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String list(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException {
		int pageNo = getPageNo(request);
		int pageSize = getPageSize(request);
		String roleId = request.getParameter("roleId");
		String json="";
		PageObject<RoleVO> pageVo = roleMgrService.queryRole(pageNo,pageSize,roleId);
		json=new JSONSerializer().exclude("*.class").deepSerialize(pageVo);
		return json;
	}
	
	@RequestMapping( value="/role_mgr/edit_role", produces = {"application/text;charset=UTF-8"})
	public @ResponseBody String editRole(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException {
		String roleId = request.getParameter("roleId");
		String roleName = request.getParameter("roleName");
		String result = roleMgrService.editRole(roleId,roleName);
		return result;
	}
	
	@RequestMapping( value="/role_mgr/add_role", produces = {"application/text;charset=UTF-8"})
	public @ResponseBody String addRole(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException {
		String roleName = request.getParameter("roleName");
		String result = roleMgrService.addRole(roleName);
		return result;
	}
	
	@RequestMapping( value="/role_mgr/del_role", produces = {"application/text;charset=UTF-8"})
	public @ResponseBody String delRole(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException {
		String roleId = request.getParameter("roleId");
		String result = roleMgrService.delRole(roleId);
		return result;
	}
	
	@RequestMapping( value="/role_mgr/del_role_func", produces = {"application/text;charset=UTF-8"})
	public @ResponseBody String delRoleFunc(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException {
		String roleId = request.getParameter("roleId");
		String funcId = request.getParameter("funcId");
		String result = roleMgrService.delRoleFunc(roleId,funcId);
		return result;
	}
	
	@RequestMapping( value="/role_mgr/add_role_func", produces = {"application/text;charset=UTF-8"})
	public @ResponseBody String addRoleFunc(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException {
		String roleId = request.getParameter("roleId");
		String funcId = request.getParameter("funcId");
		String result = roleMgrService.addRoleFunc(roleId,funcId);
		return result;
	}
	
	@RequestMapping( value="/role_mgr/role_func_list", produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String queryRoleFunc(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException {
		int pageNo = getPageNo(request);
		int pageSize = getPageSize(request);
		String roleId = request.getParameter("roleId");
		String funcShowName = request.getParameter("funcShowName");
		String funcName = request.getParameter("funcName");
		String json="";
		PageObject<RoleFuncVO> pageVo = roleMgrService.queryRoleFunc(pageNo,pageSize,roleId,funcShowName,funcName);
		json=new JSONSerializer().exclude("*.class").deepSerialize(pageVo);
		return json;
	}
	
	@RequestMapping( value="/role_mgr/role_without_func_list", produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String queryRoleWithoutFunc(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException {
		int pageNo = getPageNo(request);
		int pageSize = getPageSize(request);
		String roleId = request.getParameter("roleId");
		String funcShowName = request.getParameter("funcShowName");
		String funcName = request.getParameter("funcName");
		String json="";
		PageObject<RoleFuncVO> pageVo = roleMgrService.queryRoleWithoutFunc(pageNo,pageSize,roleId,funcShowName,funcName);
		json=new JSONSerializer().exclude("*.class").deepSerialize(pageVo);
		return json;
	}

	public RoleMgrService getRoleMgrService() {
		return roleMgrService;
	}

	public void setRoleMgrService(RoleMgrService roleMgrService) {
		this.roleMgrService = roleMgrService;
	}
	
}
