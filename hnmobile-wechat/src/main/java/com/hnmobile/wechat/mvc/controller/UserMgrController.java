package com.hnmobile.wechat.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnmobile.wechat.mvc.model.PageObject;
import com.hnmobile.wechat.mvc.model.PageVO;
import com.hnmobile.wechat.mvc.model.UserFuncVO;
import com.hnmobile.wechat.mvc.model.UserListVO;
import com.hnmobile.wechat.mvc.model.UserRoleVO;
import com.hnmobile.wechat.mvc.model.UserVO;
import com.hnmobile.wechat.mvc.service.UserMgrService;
import com.hnmobile.wechat.util.DateUtils;

import flexjson.JSONSerializer;

@Controller
public class UserMgrController extends BaseController<UserListVO>{

	Logger log = Logger.getLogger(UserMgrController.class);
	
	//<a href='#' class='del'>[生成会员卡]</a>
	String ops="\"<a href='#' class='view'>[查看]</a><a href='#' class='editRole'>[分配角色]</a><a href='#' class='editFunc'>[分配功能]</a><a href='#' class='edit'>[修改]</a><a href='#' class='del'>[删除]</a>\"";
	
	@Autowired
	private UserMgrService userMgrService ;
	
	@RequestMapping("/user_mgr/init")
	public String initUserContext(HttpServletRequest request,Model model) {
		super.initContext(request, model);
		return "user_manager/user";
	}
	
	@RequestMapping("/user_mgr/user_func/init")
	public String initUserFuncContext(HttpServletRequest request,Model model) {
		super.initContext(request, model);
		String userAccount = request.getParameter("userAccount");
		model.addAttribute("userAccount", userAccount);
		return "user_manager/user_func";
	}
	
	@RequestMapping("/user_mgr/user_role/init")
	public String initUserRoleContext(HttpServletRequest request,Model model) {
		super.initContext(request, model);
		String userAccount = request.getParameter("userAccount");
		model.addAttribute("userAccount", userAccount);
		return "user_manager/user_role";
	}
	
	@RequestMapping( value="/user_mgr/user_role/user_role_list", produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String queryUserRole(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException {
		int pageNo = getPageNo(request);
		int pageSize = getPageSize(request);
		String userAccount = request.getParameter("userAccount");
		String json="";
		PageObject<UserRoleVO> pageVo = userMgrService.queryUserRole(pageNo,pageSize,userAccount);
		json=new JSONSerializer().exclude("*.class").deepSerialize(pageVo);
		return json;
	}
	
	@RequestMapping( value="/user_mgr/user_role/user_without_role_list", produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String queryUserWithoutRole(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException {
		int pageNo = getPageNo(request);
		int pageSize = getPageSize(request);
		String userAccount = request.getParameter("userAccount");
		String json="";
		PageObject<UserRoleVO> pageVo = userMgrService.queryUserWithoutRole(pageNo,pageSize,userAccount);
		json=new JSONSerializer().exclude("*.class").deepSerialize(pageVo);
		return json;
	}
	
	@RequestMapping( value="/user_mgr/user_role/del_user_role", produces = {"application/text;charset=UTF-8"})
	public @ResponseBody String delUserRole(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException {
		String userAccount = request.getParameter("userAccount");
		int roleId = Integer.parseInt(request.getParameter("roleId"));
		String actionUser = getActionUser(request).getUserAccount();
		try{
			userMgrService.delUserRole(roleId,userAccount,actionUser);
		}catch(Exception e){
			log.error("更新用户数据失败", e);
			return "-1";
		}
		return "0";
	}
	
	@RequestMapping( value="/user_mgr/user_role/add_user_role", produces = {"application/text;charset=UTF-8"})
	public @ResponseBody String addUserRole(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException {
		String userAccount = request.getParameter("userAccount");
		int roleId = Integer.parseInt(request.getParameter("roleId"));
		String actionUser = getActionUser(request).getUserAccount();
		try{
			userMgrService.addUserRole(roleId,userAccount,actionUser);
		}catch(Exception e){
			log.error("更新用户数据失败", e);
			return "-1";
		}
		return "0";
	}
	
	@RequestMapping( value="/user_mgr/user_func/user_func_list", produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String queryUserFunc(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException {
		int pageNo = getPageNo(request);
		int pageSize = getPageSize(request);
		String userAccount = request.getParameter("userAccount");
		String funcShowName = request.getParameter("funcShowName");
		String funcName = request.getParameter("funcName");
		String isMenu = request.getParameter("isMenu");
		String json="";
		PageObject<UserFuncVO> pageVo = userMgrService.queryUserFunc(pageNo,pageSize,userAccount,funcShowName,funcName,isMenu);
		json=new JSONSerializer().exclude("*.class").deepSerialize(pageVo);
		return json;
	}
	
	@RequestMapping( value="/user_mgr/user_func/user_without_func_list", produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String queryUserWithoutFunc(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException {
		int pageNo = getPageNo(request);
		int pageSize = getPageSize(request);
		String userAccount = request.getParameter("userAccount");
		String funcShowName = request.getParameter("funcShowName");
		String funcName = request.getParameter("funcName");
		String json="";
		PageObject<UserFuncVO> pageVo = userMgrService.queryUserWithoutFunc(pageNo,pageSize,userAccount,funcShowName,funcName);
		json=new JSONSerializer().exclude("*.class").deepSerialize(pageVo);
		return json;
	}
	
	@RequestMapping( value="/user_mgr/user_func/add_user_func", produces = {"application/text;charset=UTF-8"})
	public @ResponseBody String addUserFunc(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException {
		String userAccount = request.getParameter("userAccount");
		int funcId = Integer.parseInt(request.getParameter("funcId"));
		String actionUser = getActionUser(request).getUserAccount();
		try{
			userMgrService.addUserFunc(funcId,userAccount,actionUser);
		}catch(Exception e){
			log.error("更新用户数据失败", e);
			return "-1";
		}
		return "0";
	}
	
	@RequestMapping( value="/user_mgr/user_func/del_user_func", produces = {"application/text;charset=UTF-8"})
	public @ResponseBody String delUserFunc(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException {
		String userAccount = request.getParameter("userAccount");
		int funcId = Integer.parseInt(request.getParameter("funcId"));
		String actionUser = getActionUser(request).getUserAccount();
		try{
			userMgrService.delUserFunc(funcId,userAccount,actionUser);
		}catch(Exception e){
			log.error("更新用户数据失败", e);
			return "-1";
		}
		return "0";
	}
	
	@RequestMapping( value="/user_mgr/list", produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String list(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException {
		int pageNo = getPageNo(request);
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		String json="";
		PageVO<UserListVO> pageVo = userMgrService.getUserList(pageNo,pageSize);
		json = super.pageVolist2json(pageVo,ops);
		return json;
	}
	
	@RequestMapping( value="/user_mgr/query_user", produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String queryUser(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException {
		String userAccount = request.getParameter("userName");
		int pageNo = getPageNo(request);
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		String json="";
		PageVO<UserListVO> pageVo = userMgrService.queryUser(pageNo,pageSize,userAccount);
		json = super.pageVolist2json(pageVo,ops);
		return json;
	}
	
	@RequestMapping( value="/user_mgr/get_user", produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String getUserByName(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException {
		String userAccount = request.getParameter("userName");
		if( userAccount==null )return "[]";
		UserVO uvo = userMgrService.getUser(userAccount,null,null,null,null);
		if(uvo==null) return "[]";
		String registerTime = DateUtils.formatDateTime(uvo.getRegisterTime());
		String lock = uvo.getLock();
		String json="[{";
		json+="\"userName\":\""+uvo.getUserAccount()+"\",";
		json+="\"lock\":\""+lock+"\",";
		json+="\"role\":\""+uvo.getRoleId()+"\",";
		json+="\"registerTime\":\""+registerTime+"\"";
		json+="}]";
		return json;
	}
	
	@RequestMapping( value="/user_mgr/update_user", produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String updateUserInfo(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException {
		String userAccount = request.getParameter("userName");
		String roleId = request.getParameter("roleId");
		String lock = request.getParameter("lock");
		String registerTime = request.getParameter("registerTime");
		try{
			userMgrService.updateUserInfo(userAccount, roleId, lock, registerTime);
		}catch(Exception e){
			log.error("更新用户数据失败", e);
			return "-1";
		}
		String json="[{";
		json+="\"userName\":\""+userAccount+"\",";
		json+="\"lock\":\""+lock+"\",";
		json+="\"role\":\""+roleId+"\",";
		json+="\"registerTime\":\""+registerTime+"\"";
		json+="}]";
		return json;
	}
	
	@RequestMapping( value="/user_mgr/add_user", produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String addUserInfo(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException {
		String userAccount = request.getParameter("userName");
		String roleId = request.getParameter("roleId");
		String lock = request.getParameter("lock");
		try{
			userMgrService.addUserInfo(userAccount, Integer.parseInt(roleId), lock,null,null,null,null,null);
		}catch(Exception e){
			log.error("更新用户数据失败", e);
			return "-1";
		}
		return "0";
	}
	
	@RequestMapping( value="/user_mgr/delete_user", produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String delUserInfo(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException {
		String userAccount = request.getParameter("userName");
		try{
			userMgrService.delUserInfo(userAccount);
		}catch(Exception e){
			log.error("更新用户数据失败", e);
			return "-1";
		}
		return "0";
	}
	
	/**
	 * 返回1，账号已经存在。返回 0 ，账号不存在
	 * @param request
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@RequestMapping( value="/user_mgr/check_user_account", produces = {"application/text;charset=UTF-8"})
	public @ResponseBody String getUser(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException {
		String userAccount = request.getParameter("userAccount");
		boolean exists = userMgrService.isUserAccountExists(userAccount);
		if(exists)return "1";
		return "0";
	}

	public UserMgrService getUserMgrService() {
		return userMgrService;
	}

	public void setUserMgrService(UserMgrService userMgrService) {
		this.userMgrService = userMgrService;
	}
}
