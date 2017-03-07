package com.hnmobile.wechat.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.hnmobile.wechat.mvc.SpringContext;
import com.hnmobile.wechat.mvc.model.SelectorVO;

public class CommonDao extends JdbcBaseDao{

	public List<SelectorVO> getRoleSelector(){
		
		String sql="SELECT ROLE_ID ID,ROLE_NAME NAME FROM LOTTERY.T_ROLE";
		List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
		List<SelectorVO> selectors = new ArrayList<SelectorVO>();
		
		for( Map<String,Object> result:results ){
			int roleId = (Integer) result.get("ID");
			String name = (String) result.get("Name");
			SelectorVO selectorVO = new SelectorVO();
			selectorVO.setId(roleId+"");
			selectorVO.setName(name);
			selectors.add(selectorVO);
		}
		return selectors;
	}
	
	public List<SelectorVO> getCardTypeSelector(){
		
		String sql="SELECT CARD_TYPE ID,CARD_TYPE_NAME Name FROM LOTTERY.T_CARD_TYPE";
		List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
		List<SelectorVO> selectors = new ArrayList<SelectorVO>();
		
		for( Map<String,Object> result:results ){
			String roleId = (String) result.get("ID");
			String name = (String) result.get("Name");
			SelectorVO selectorVO = new SelectorVO();
			selectorVO.setId(roleId);
			selectorVO.setName(name);
			selectors.add(selectorVO);
		}
		return selectors;
	}

	public List<SelectorVO> getMerchantSelector( String merchantAccounts ) {
		
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		String sql="SELECT B.MERCHANT_NAME NAME,B.MERCHANT_ACCOUNT ID FROM LOTTERY.T_MERCHANT B ";
		sql +="WHERE B.MERCHANT_ACCOUNT IN("+merchantAccounts+")";
		
		List<SelectorVO> selectors = new ArrayList<SelectorVO>();
		
		for( Map<String,Object> result:results ){
			String merchantAccount = (String) result.get("ID");
			String name = (String) result.get("Name");
			SelectorVO selectorVO = new SelectorVO();
			selectorVO.setId(merchantAccount);
			selectorVO.setName(name);
			selectors.add(selectorVO);
		}
		return selectors;
	}
	
	public static void main( String[]args ){
		
		CommonDao dao = SpringContext.ctx.getBean("commonDao", CommonDao.class);
		
		List<SelectorVO> selects = dao.getMerchantSelector("ss");
		
		System.out.println(selects.size());
	}
	
}
