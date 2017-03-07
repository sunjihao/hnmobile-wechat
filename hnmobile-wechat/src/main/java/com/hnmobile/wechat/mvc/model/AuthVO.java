package com.hnmobile.wechat.mvc.model;

public class AuthVO {

protected String memberAccount;
	
	protected String merchantAccount;
	
	protected String actionUserAccount;

	public String getMemberAccount() {
		return memberAccount;
	}

	public String getMerchantAccount() {
		return merchantAccount;
	}

	public String getActionUserAccount() {
		return actionUserAccount;
	}

	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}

	public void setMerchantAccount(String merchantAccount) {
		this.merchantAccount = merchantAccount;
	}

	public void setActionUserAccount(String actionUserAccount) {
		this.actionUserAccount = actionUserAccount;
	}
}
