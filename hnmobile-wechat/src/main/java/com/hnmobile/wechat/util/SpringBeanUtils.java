package com.hnmobile.wechat.util;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import com.hnmobile.wechat.mvc.SpringContext;

public class SpringBeanUtils{

	public static Object getBean(String beanId,Class clazz){
		return SpringContext.ctx.getBean(beanId, clazz);
	}
	
}
