package com.example.okhttpdemo;

import java.io.Serializable;

/**
 * @author bx
 * @date 2014年12月25日
 * @description
 */
public class VersionInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7198132585400691931L;
	private  String versionUrl;
	private  int areConsistent = -1;
	private  int forceStatus = -1;
	private  String currentVersion;
	private  String versionName;
	private  String updateDetails;
	private  int versionType=-1;
	private  String updateTime;
	public String getVersionUrl() {
		return versionUrl;
	}
	public void setVersionUrl(String versionUrl) {
		this.versionUrl = versionUrl;
	}
	public int getAreConsistent() {
		return areConsistent;
	}
	public void setAreConsistent(int areConsistent) {
		this.areConsistent = areConsistent;
	}
	public int getForceStatus() {
		return forceStatus;
	}
	public void setForceStatus(int forceStatus) {
		this.forceStatus = forceStatus;
	}
	public String getCurrentVersion() {
		return currentVersion;
	}
	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getUpdateDetails() {
		return updateDetails;
	}
	public void setUpdateDetails(String updateDetails) {
		this.updateDetails = updateDetails;
	}
	public int getVersionType() {
		return versionType;
	}
	public void setVersionType(int versionType) {
		this.versionType = versionType;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	

}
