package com.hnmobile.wechat.mvc.model;

import java.util.List;

public class PageVO<T> {

	private int total;
	
	private List<T> listVO;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getListVO() {
		return listVO;
	}

	public void setListVO(List<T> listVO) {
		this.listVO = listVO;
	}
	
}
