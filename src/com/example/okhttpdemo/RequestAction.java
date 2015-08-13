package com.example.okhttpdemo;

import java.util.HashMap;
import java.util.Map;

import com.younchen.utils.http.HttpUtil;
import com.younchen.utils.http.JsonParser;


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
		params.put("phone",userId);
		params.put("pwd", psw);
		String json= HttpUtil.doPost(url, params, defaultHeader);

		LoginResult result=JsonParser.parse(json, LoginResult.class);
		if(result.getCode()==0){
			String token=result.getData().getToken();
			putToken(token);
		}
	}
	
	public void UploadForum(){
		
	}
	
	
}
