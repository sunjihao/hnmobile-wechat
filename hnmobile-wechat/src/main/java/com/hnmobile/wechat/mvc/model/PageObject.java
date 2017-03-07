package com.hnmobile.wechat.mvc.model;

import java.util.List;

public class PageObject<T> {

	private int totalCount;
	
	private List<T> datas;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

}
