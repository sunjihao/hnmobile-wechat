package com.hnmobile.wechat.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 存储键值对，键格式为，[大类]_[键]
 * @author sunjihao
 *
 */
public class KeyCode {

	/**
	 * 卡状态大类
	 */
	private static final String CARD_STATUS="CARD_STATUS";
	
	/**
	 * 卡类型大类
	 */
	private static final String CARD_TYPE="CARD_TYPE";
	
	private static Map<String,String> keyCode = new HashMap<String,String>();
	
	static{
		keyCode.put(CARD_STATUS+"_N", "失效");
		keyCode.put(CARD_STATUS+"_Y", "生效");
		
		keyCode.put(CARD_TYPE+"_normal", "普通卡");
		keyCode.put(CARD_TYPE+"_silver", "银卡");
		keyCode.put(CARD_TYPE+"_gold", "金卡");
		keyCode.put(CARD_TYPE+"_platina", "白金卡");
		keyCode.put(CARD_TYPE+"_diamond", "钻石卡");
	}
	
	public static String getCardStatus( String key ){
		return keyCode.get( CARD_STATUS+"_"+key);
	}
	
	public static String getCardType( String key ){
		return keyCode.get( CARD_TYPE+"_"+key);
	}
	
}
