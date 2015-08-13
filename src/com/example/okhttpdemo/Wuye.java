package com.example.okhttpdemo;

import java.io.Serializable;

/**
 * @author longquan
 * @date 2015年6月12日
 * @description 
 */
public class Wuye implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String property_head;
	private String property_tell;
	private String company_name;
	private String id;
	private String property_present;
	private String property_username;
	private String user_id;
	
	public String getProperty_head() {
		return property_head;
	}
	public void setProperty_head(String property_head) {
		this.property_head = property_head;
	}
	public String getProperty_tell() {
		return property_tell;
	}
	public void setProperty_tell(String property_tell) {
		this.property_tell = property_tell;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProperty_present() {
		return property_present;
	}
	public void setProperty_present(String property_present) {
		this.property_present = property_present;
	}
	public String getProperty_username() {
		return property_username;
	}
	public void setProperty_username(String property_username) {
		this.property_username = property_username;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
