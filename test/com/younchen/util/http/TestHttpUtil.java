package com.younchen.util.http;

import java.io.IOException;
import java.util.HashMap;

import android.test.AndroidTestCase;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.younchen.utils.http.HttpUtil;

public class TestHttpUtil extends AndroidTestCase {

	public void testGet() {

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("page", "value");
		params.put("count", "1");
		HttpUtil.get("http://www.baidu.com", params, null,new Callback() {
			
			@Override
			public void onResponse(Response response) throws IOException {
				// TODO Auto-generated method stub
				String str=response.body().string();
				System.out.println(str);
			}
			
			@Override
			public void onFailure(Request arg0, IOException arg1) {
				// TODO Auto-generated method stub
				System.out.println(arg1.getMessage());
				System.out.println(arg0.body().toString());
				
			}
		});
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
