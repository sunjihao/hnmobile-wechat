package com.hnmobile.wechat.mvc.authservice;


public class AuthenException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AuthenException(String err) {
        super(err);
    }
	
}
