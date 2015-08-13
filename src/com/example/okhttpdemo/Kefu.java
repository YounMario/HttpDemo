package com.example.okhttpdemo;

import java.io.Serializable;

/**
 * @author longquan
 * @date 2015年6月12日
 * @description 
 */
public class Kefu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String provinceCompanyName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProvinceCompanyName() {
		return provinceCompanyName;
	}
	public void setProvinceCompanyName(String provinceCompanyName) {
		this.provinceCompanyName = provinceCompanyName;
	}
	
}
