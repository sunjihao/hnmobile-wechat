package com.hnmobile.wechat.mvc.authservice;

import java.util.List;

public class AuthenService {
	
	public static String getMultiIdsStr(List<String> merchantAccounts){
		String merchantAccount="";
		if(merchantAccounts==null||merchantAccounts.isEmpty()){
			return "''";
		}
		for(int i=0;i<merchantAccounts.size();i++){
			if(i<merchantAccounts.size()-1){
				merchantAccount+="'"+merchantAccounts.get(i)+"',";
			}else{
				merchantAccount+="'"+merchantAccounts.get(i)+"'";
			}
		}
		return merchantAccount;
	}
}
