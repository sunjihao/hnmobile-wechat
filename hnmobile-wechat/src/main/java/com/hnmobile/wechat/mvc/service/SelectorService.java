package com.hnmobile.wechat.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hnmobile.wechat.dao.CommonDao;
import com.hnmobile.wechat.mvc.model.SelectorVO;

public class SelectorService {

	@Autowired
	private CommonDao commonDao;
	/**
	 * 获取角色列表的SelectorVO
	 */
	public List<SelectorVO> getSelectRole(){
		List<SelectorVO> selectors = commonDao.getRoleSelector();
		return selectors;
	}
	
	/**
	 * 获取角色列表的SelectorVO
	 */
	public List<SelectorVO> getSelectCardTypes(){
		List<SelectorVO> selectors = commonDao.getCardTypeSelector();
		return selectors;
	}
	
}
