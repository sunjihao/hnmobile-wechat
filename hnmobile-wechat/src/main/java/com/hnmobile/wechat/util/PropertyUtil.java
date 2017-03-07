package com.hnmobile.wechat.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.hnmobile.wechat.mvc.Constant;

/**
 * 用于读取properties文件
 * @author sunjihao
 *
 */
public class PropertyUtil {
	
	static Logger log = Logger.getLogger(PropertyUtil.class);
	
	Properties prop = new Properties();
	InputStream in = Object.class.getResourceAsStream("system.properties");
	
	public static Map<String,String> properties = new HashMap<String,String>();
	
//	static {
//		String[]names = new String[]{Constant.MERCHANT_CARD_UPLOAD_PATH,
//				Constant.MERCHANT_CARD_DEFAULT_PIC_SIDE1_PATH,Constant.MERCHANT_CARD_DEFAULT_PIC_SIDE2_PATH};
//		Properties prop = new Properties();
//		InputStream in = PropertyUtil.class.getResourceAsStream("/system.properties");
//		try {
//			prop.load(in);
//			for( String name:names ){
//				String value = prop.getProperty(name).trim();
//				if(name.equals(Constant.MERCHANT_CARD_UPLOAD_PATH)){
//					File file = new File(value);
//					if(!file.exists()){
//						if(!file.mkdirs()){
//							log.error("!!!!!!!! init properties error,make dirs fail "+Constant.MERCHANT_CARD_UPLOAD_PATH+" !!!!!!!");
//						}
//					}
//				}
//				
//				properties.put(name, value);
//			}
//		}catch( IOException e){
//			log.error("!!!!!!!! init properties error!!!!!!!", e);
//		}
//	}
	
	public static void main( String[]args ){
	}
	
}
