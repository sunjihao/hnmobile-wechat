package com.hnmobile.wechat.mvc;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * 
 * @author     : sunjihao
 *
 */
public class CodeFilter implements Filter {
	//默认编码格式UTF-8
	private static final String DEFAULT_ENCODE = "UTF-8";
	
	private String encodeName; // 编码格式
	private static Logger logger = Logger.getLogger(CodeFilter.class);  

	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		filterEncode( request,response );
		chain.doFilter(request, response);
	}
	
	private void filterEncode( ServletRequest request, ServletResponse response ) throws UnsupportedEncodingException{
		try {
			if (encodeName == null || "".equals(encodeName.trim())) {
				request.setCharacterEncoding(DEFAULT_ENCODE);
				response.setCharacterEncoding(DEFAULT_ENCODE);
			} else {
				request.setCharacterEncoding(encodeName);
			}
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedEncodingException("编码格式过滤错误，请确认web.xml填入了正确的编码格式");
		}
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		//获取web.xml配置的<param-name>encodeName</param-name>的值
		this.setEncodeName(fConfig.getInitParameter("encodeName")); 
	}

	public String getEncodeName() {
		return encodeName;
	}

	public void setEncodeName(String encodeName) {
		this.encodeName = encodeName;
	}

}

