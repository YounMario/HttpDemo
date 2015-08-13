package com.example.okhttpdemo;

import java.io.Serializable;


/**
 * @author kang
 * @date 2014年11月26日
 * @description
 */
public class LoginResult extends Result {
	private static final long serialVersionUID = -8300034087001944871L;
	private LoginData data;

	public LoginData getData() {
		return data;
	}

	public void setData(LoginData data) {
		this.data = data;
	}

	public static class LoginData implements Serializable {
		private static final long serialVersionUID = -7669747919244404218L;
		private UserInfo userInfo;
		private String token;
		private VersionInfo currentVersion;
		private String weather="";
		private Kefu kefu;
		private Wuye property;
		
		
		public String getWeather() {
			return weather;
		}

		public void setWeather(String weather) {
			this.weather = weather;
		}

		public VersionInfo getVersionInfo() {
			if (currentVersion == null) {
				currentVersion = new VersionInfo();
			}
			return currentVersion;
		}

		public void setCurrentVersion(VersionInfo currentVersion) {
			this.currentVersion = currentVersion;
		}

		public UserInfo getUserInfo() {
			return userInfo;
		}

		public void setUserInfo(UserInfo userInfo) {
			this.userInfo = userInfo;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public Kefu getKefu() {
			return kefu;
		}

		public void setKefu(Kefu kefu) {
			this.kefu = kefu;
		}

		public Wuye getProperty() {
			return property;
		}

		public void setProperty(Wuye property) {
			this.property = property;
		}

	}

}
