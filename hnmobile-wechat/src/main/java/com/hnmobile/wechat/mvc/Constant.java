package com.hnmobile.wechat.mvc;

public interface Constant {

	public String IS_USER_VALIDATE="IS_USER_VALIDATE";
	
	public String USER_OBJ="USER_OBJ";
	
	public String VALIDATE_USER="VALIDATE_USER";
	
	public final int MAX_ERROR_LOGIN_TIME=3;
	
	public final String USER_ACCOUNT="userAccount";
	
	public final String CONTEXT_PATH="contextPath";
	
	/**
	 * 管理员角色ID
	 */
	public final int adminRoleId=1;
	
	/**
	 * 普通会员角色ID
	 */
	public final int memberRoleId=5;
	
	/**
	 * 123456
	 */
	public final String defaultMD5Passwd="e10adc3949ba59abbe56e057f20f883e";
	
	public final String defaultPasswd="123456";
	
	public final String emailAddr="sunjihao@126.com";
	
	/**
	 * 商家店员角色ID
	 */
	public final int merchantStaffRoleId=6;
	
	public final String MERCHANT_CARD_UPLOAD_BASE_PATH="MERCHANT_CARD_UPLOAD_BASE_PATH";
//	public final String MERCHANT_CARD_DEFAULT_PIC_SIDE1_PATH="MERCHANT_CARD_DEFAULT_PIC_SIDE1_PATH";
//	public final String MERCHANT_CARD_DEFAULT_PIC_SIDE2_PATH="MERCHANT_CARD_DEFAULT_PIC_SIDE2_PATH";
	
	public interface AUTION_STATUS{
//		IN 初始状态,AU已挂牌，VY已竞拍，AG卖家已同意，PY 已付款，PB已发布，RD已阅读，RC 已收款  
		public final String IN="IN";
		public final String AU="AU";
		public final String VY="VY";
		public final String AG="AG";
		public final String DG="DG";//卖家不同意
		public final String PY="PY";
		public final String PB="PB";
		public final String RD="RD";
		public final String RC="RC";
		
	}
	
}
