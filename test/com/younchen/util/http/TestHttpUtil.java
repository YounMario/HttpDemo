package com.younchen.util.http;

import android.test.AndroidTestCase;

import com.squareup.okhttp.Request;
import com.younchen.utils.http.HttpRequestBuilder;
import com.younchen.utils.http.HttpRequestBuilder.HttpMethod;
import com.younchen.utils.http.handler.HttpRequestHandler;
import com.younchen.utils.http.handler.HttpRequestHandler.HttpCallBack;

public class TestHttpUtil extends AndroidTestCase {

	public void testGet() {

		HttpRequestBuilder builder=new HttpRequestBuilder();
		Request request=builder.addParams("fuck", "deam").method(HttpMethod.GET).url("http://www.baidu.com")
		.build();
		
		HttpRequestHandler handler=new HttpRequestHandler(request,new HttpCallBack() {
			
			@Override
			public void onSuccess(String body) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFail(String message) {
				// TODO Auto-generated method stub
				
			}
		});
		
		handler.execute();
		
		

	}

}
