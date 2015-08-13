package com.younchen.util.http;

import java.util.HashMap;

import android.test.AndroidTestCase;

import com.example.okhttpdemo.RequestAction;
import com.younchen.utils.http.HttpUtil;

public class TestHttpUtil extends AndroidTestCase {

	public void testGet() {

//		HashMap<String, String> params = new HashMap<String, String>();
//		params.put("page", "value");
//		params.put("count", "1");
//		HttpUtil.get("http://www.baidu.com", params, null);

	}

	public void testPost() {
		RequestAction requestAction=new RequestAction();
		requestAction.UserLogin("13167390434", "123456");
	}
}
