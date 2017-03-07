package com.hnmobile.wechat.mvc.controller;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.hnmobile.wechat.mvc.Constant;
import com.hnmobile.wechat.mvc.model.PageVO;
import com.hnmobile.wechat.mvc.model.SelectorVO;
import com.hnmobile.wechat.mvc.model.UserVO;

public class BaseController<T> extends RequestMappingHandlerMapping{
	
	public String initContext(HttpServletRequest request,Model model) {
		UserVO userVO = (UserVO) request.getSession().getAttribute(Constant.USER_OBJ);
		model.addAttribute(Constant.USER_ACCOUNT, userVO.getUserAccount());
		model.addAttribute( Constant.CONTEXT_PATH, request.getContextPath());
		model.addAttribute("now", new Date().getTime()+"");
		return "";
	}
	
	protected int getPageNo( HttpServletRequest request ){
		String pno = request.getParameter("pageNo");
		int pageNo = (pno==null||pno.equals("0"))?0:Integer.parseInt(pno)-1;
		return pageNo;
	}
	
	protected int getPageSize( HttpServletRequest request ){
		String psz = request.getParameter("pageSize");
		int pageSize = psz==null?1:Integer.parseInt(psz);
		return pageSize;
	}
	
	protected UserVO getActionUser( HttpServletRequest request ){
		UserVO userVo = (UserVO) request.getSession().getAttribute(Constant.USER_OBJ);
		return userVo;
	}

	/**
	 * 将VO列表转换成json
	 * "{\"totalCount\":3,\"datas\":[[\"Saruar\",\"2012/03/01\",\"Member\",\"<span class='label-warning label label-default'>Pending</span>\"],[\"Saruar\",\"2012/03/01\",\"Member\",\"<span class='label-warning label label-default'>Pending</span>\"],[\"Saruar\",\"2012/03/01\",\"Member\",\"<span class='label-warning label label-default'>Pending</span>\"]]}"
	 * @return
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	protected String pageVolist2json( PageVO<T> pageVO,String ops) throws IllegalArgumentException, IllegalAccessException{
		int total = pageVO.getTotal();
		List<T> volist = pageVO.getListVO();
		int size = volist.size();
		String json="{\"totalCount\":"+total+",\"datas\":[";
		for( int j=0;j<size;j++ ){
			Object vo = volist.get(j);
			Field[] fields = vo.getClass().getDeclaredFields();
			for( int i=0;i<fields.length;i++ ){
				if(i==0)json+="[";
				Field field = fields[i];
				field.setAccessible( true );
				String val = "\""+field.get(vo)+"\"";
				if(i<fields.length-1)json+=val+",";
				else {
					if(ops==null){
						json+=val+",\"<a href='#' class='view'>[查看]</a><a href='#' class='edit'>[修改]</a><a href='#' class='del'>[删除]</a>\"";
					}else{
						json+=val+","+ops;
					}
					json+="]";
				}
			}
			if(j<size-1){
				json+=",";
			}

		}
		json+= "]}";
		return json;
	}
	
	/**
	 * 将selectorVO列表转换成json
	 * 
	 * @return [ {'id':'XXX','name':'XXX'} ]
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	protected String selectorVOlist2json( List<SelectorVO> selectorVOs) throws IllegalArgumentException, IllegalAccessException{
		int size = selectorVOs.size();
		String json="[";
		for( int j=0;j<size;j++ ){
			SelectorVO vo = selectorVOs.get(j);
			json+="{";
			json+= "\"id\":\""+vo.getId()+"\",";
			json+="\"name\":\""+vo.getName()+"\"}";
			if(j<size-1){
				json+=",";
			}

		}
		json+= "]";
		return json;
	}
	
}
