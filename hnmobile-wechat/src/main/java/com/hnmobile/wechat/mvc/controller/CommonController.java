package com.hnmobile.wechat.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnmobile.wechat.mvc.model.SelectorVO;
import com.hnmobile.wechat.mvc.model.UserListVO;
import com.hnmobile.wechat.mvc.service.SelectorService;

/**
 * 设置下拉框
 * @author sunjihao
 * 
 */
@Controller
public class CommonController extends BaseController<UserListVO>{

	@Autowired
	private SelectorService selectorService ;
	
	@RequestMapping( value="/roleList", produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String listRole(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException {
		List<SelectorVO> selectorVos = selectorService.getSelectRole();
		String json=selectorVOlist2json(selectorVos);
		return json;
	}
	
}
