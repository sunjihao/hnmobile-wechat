package com.hnmobile.wechat.mvc;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContext {
	
	public static AbstractApplicationContext ctx =new ClassPathXmlApplicationContext(
            new String []{"root-context.xml","beans.xml"});
		
}
