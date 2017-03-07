package com.hnmobile.wechat.mvc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.hnmobile.wechat.util.URLUtil;


/**
 * 
 * @author:sunjihao   
 *
 */
public class AuthenFilter implements Filter {
	//默认编码格式UTF-8
	private static final String DEFAULT_ENCODE = "UTF-8";
	
	private String encodeName; // 编码格式
	private static Logger logger = Logger.getLogger(AuthenFilter.class);  

	public void destroy() {

	}
	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session=req.getSession();
		
        Object userVO = session.getAttribute( Constant.USER_OBJ );
        String url = req.getRequestURI();
        
        if( URLUtil.isLoginOrVerifyCodeUrl(url) ){
        	chain.doFilter(request, response);
        	return;
        }
    	
        if( URLUtil.isActionUrl(url) ){
            if (URLUtil.isAppUrl(url)) {
                chain.doFilter(request, response);
            } else {
                if (userVO==null) {
                    logger.info("用户未登陆……");
                    // 重定向方式
                    String contextUrl = req.getContextPath();
                    res.sendRedirect(contextUrl+"/login.do");
                    return;
                } else {
                    chain.doFilter(request, response);
                }
            }
        	
        }else{
        	chain.doFilter(request, response);
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

