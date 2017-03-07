package com.hnmobile.wechat.mvc;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
* Application监听器,Servlet中的Application即ServletContext
* @author sunjihao
*/
public class ApplicationListener implements ServletContextListener,
		ServletContextAttributeListener {
	
	/**
	 * application销毁时触发的事件
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Application销毁："+arg0.getServletContext());
	}
	
	/**
	 * application初始化时触发的方法
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Application创建："+arg0.getServletContext());
	}
	
	/**
	 * application中添加属性值时触发的方法
	 */
	@Override
	public void attributeAdded(ServletContextAttributeEvent arg0) {
		System.out.println("Application添加新属性：key="+arg0.getName()+"  value="+arg0.getValue());
	}
	
	/**
	 * application中删除属性值时触发的方法
	 */
	@Override
	public void attributeRemoved(ServletContextAttributeEvent arg0) {
		System.out.println("Application移除属性：key="+arg0.getName()+"  value="+arg0.getValue());
	}
	
	/**
	 * application中替换属性值时触发的方法
	 */
	@Override
	public void attributeReplaced(ServletContextAttributeEvent arg0) {
		System.out.println("Application替换属性：key="+arg0.getName()+"  value="+arg0.getValue());
	}

}
