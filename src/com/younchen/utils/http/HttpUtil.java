package com.younchen.utils.http;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

/**
 * see also https://github.com/square/okhttp/wiki/Recipes
 * 
 * @author longquan
 *
 */
public class HttpUtil {

	private final static OkHttpClient client = new OkHttpClient();

	public static Call sendRequst(Request request, Callback callBack) {
		Call c = client.newCall(request);
		c.enqueue(callBack);
		return c;
	}

}
