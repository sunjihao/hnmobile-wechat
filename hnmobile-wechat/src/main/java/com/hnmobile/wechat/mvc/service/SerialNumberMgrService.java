package com.hnmobile.wechat.mvc.service;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import com.hnmobile.wechat.util.DateUtils;


public class SerialNumberMgrService {

	//10位(10亿)的自增长int
	private AtomicInteger serailNumber=new AtomicInteger(1000000000);
	
	private String timestamp;
	
	public void init(){
		timestamp=DateUtils.formatLogTime(new Date());
	}

	public String getSerialNumber(){
		return timestamp+serailNumber.incrementAndGet();
	}

}
