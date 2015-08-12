package com.younchen.utils.http;

import java.util.HashMap;
import java.util.Map;


public class RequestAction {

	private Map<String,String> defaultHeader;
	
	public RequestAction(){
		defaultHeader=new HashMap<String, String>();
		defaultHeader.put("Accept", "application/json");
	}
	
	public void putToken(String token){
		
		defaultHeader.put("Cookie", "JSESSIONID="+token);
	}
	
	public void putVersionCode(String versionCode){
		defaultHeader.put("VersionCode", versionCode);
	}
	
	public void putChannelId(String channelId){
		defaultHeader.put("ChannelID", channelId);
	}
	
	
	public void UserLogin(String userId,String psw){
		String url = "http://younchen.eatuo.com:10006/quangui/user/checkLogin";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("phone", "13167390434");
		params.put("pwd", "123456");
		putToken("aaaaaaaaaaddddddcccii");
		String str = HttpUtil.doPost(url, params, defaultHeader);
		System.out.println(str);
	}
	
	
}
