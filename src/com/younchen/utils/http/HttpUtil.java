package com.younchen.utils.http;

import java.util.Map;
import java.util.Set;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class HttpUtil {

	private final static OkHttpClient client = new OkHttpClient();

	 
	public static String get(String url, Map<String, String> params,
			Map<String, String> header) {
		url = bindGetRequestParam(url, params);
		Request.Builder requestBuilder = new Request.Builder().url(url);
		bindHeader(requestBuilder, header);
		Response response;
		try {
			response = client.newCall(requestBuilder.build()).execute();
			if (response.isSuccessful()) {
				return response.body().string();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

 
	public static void get(String url, Map<String, String> params,
			Map<String, String> header, Callback callBack) {
		url = bindGetRequestParam(url, params);
		Request.Builder requestBuilder = new Request.Builder().url(url);
		bindHeader(requestBuilder, header);
		client.newCall(requestBuilder.build()).enqueue(callBack);
	}


 
	public static String doPost(String url, Map<String, String> params,
			Map<String, String> header) {

		FormEncodingBuilder formBodyBuilder = new FormEncodingBuilder();
		bindPrams(formBodyBuilder, params);
		Request.Builder requestBuilder = new Request.Builder();
		bindHeader(requestBuilder, header);
		Request request ;
		if(params==null){
			  request = requestBuilder.url(url)
					.build();
		}else{
			request = requestBuilder.url(url).post(formBodyBuilder.build())
					.build();
		}
		Response response = null;
		try {
			response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				return response.body().string();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	
	public void upLoad(String url) {

	}
	
	
	public void downLoad(){
		
	}

 
	private static void bindPrams(FormEncodingBuilder builder,
			Map<String, String> params) {
		if (params == null)
			return;
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			String parameValue = params.get(key);
			builder.add(key, parameValue);
		}
	}
 

 
	private static void bindHeader(Request.Builder builder,
			Map<String, String> header) {
		if (header == null)
			return;
		Set<String> keySet = header.keySet();
		for (String key : keySet) {
			String parameValue = header.get(key);
			builder.addHeader(key, parameValue);
		}
	}

	private static String bindGetRequestParam(String url,
			Map<String, String> param) {
		if (param == null)
			return url;
		Set<String> keySet = param.keySet();
		StringBuffer sb = new StringBuffer();
		sb.append(url);
		sb.append("?");
		for (String key : keySet) {
			sb.append(key);
			sb.append("=");
			sb.append(param.get(key));
			sb.append("&");
		}
		return sb.subSequence(0, sb.length() - 1).toString();
	}
}
