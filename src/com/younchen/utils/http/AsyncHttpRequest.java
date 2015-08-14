package com.younchen.utils.http;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Handler;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.younchen.utils.http.thread.Excutor;

import android.os.Message;

/**
 * 异步请求类
 * 
 * @author longquan
 *
 */
public class AsyncHttpRequest {

	public static final String GET = "GET";
	public static final String POST = "POST";

	private String url;
	private HashMap<String, String> header;
	private HashMap<String, String> params;
	private Excutor excutor;
	private RequestCallBack callBack;

	private HttpHandler httpHandler;
	private String method;
	private List<FileDiscription> flist;

	private AsyncHttpRequest() {
		excutor = new Excutor();
	}

	private AsyncHttpRequest(String url, HashMap<String, String> header,
			HashMap<String, String> params, String method,
			RequestCallBack callBack, List<FileDiscription> flist) {
		this();
		this.url = url;
		this.header = header;
		this.params = params;
		this.callBack = callBack;
		this.method = method;
		this.flist = flist;
		if (callBack != null)
			httpHandler = new HttpHandler(this.callBack);
		checkMethod();
	}

	/**
	 * 若没有设置method 默认为Get
	 */
	private void checkMethod() {
		if (method == null)
			this.method = GET;
	}

	public void execute() {
		// excutor.execute(new HandlerRunnable(new ));
		excutor.execute(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				HttpUtil.request(url, params, header, flist, new Callback() {
					@Override
					public void onResponse(Response arg0) throws IOException {
						// TODO Auto-generated method stub
						String str = arg0.body().string();
						httpHandler.onSuccess(str);
					}

					@Override
					public void onFailure(Request arg0, IOException arg1) {
						// TODO Auto-generated method stub
						httpHandler.onFail();
					}
				}, method);
			}
		});
	}

	public static class Builder {

		private String url;
		private HashMap<String, String> header;
		private HashMap<String, String> params;
		private List<FileDiscription> flist;
		private String method;
		private RequestCallBack callBack;

		public Builder() {
			header = new HashMap<String, String>();
			params = new HashMap<String, String>();
			flist = new ArrayList<FileDiscription>();
		}

		public Builder url(String url) {
			this.url = url;
			return this;
		}

		public Builder method(String method) {
			this.method = method;
			return this;
		}

		public Builder addHeader(String headerKey, String headerValue) {
			header.put(headerKey, headerValue);
			return this;
		}

		public Builder addParams(String paramKey, String paramValue) {
			params.put(paramKey, paramValue);
			return this;
		}

		public Builder addFile(FileDiscription fileDiscription) {
			flist.add(fileDiscription);
			return this;
		}

		public Builder callBack(RequestCallBack callBack) {
			this.callBack = callBack;
			return this;
		}

		public AsyncHttpRequest build() {
			return new AsyncHttpRequest(url, header, params, method, callBack,
					flist);
		}

	}

	public interface RequestCallBack {

		public void onSuccess(String result);

		public void onFail();

	}

	public static class HttpHandler extends Handler {

		private int SUCCESS = 0x01;
		private int FAIL = 0x02;

		private RequestCallBack callBack;

		public HttpHandler(RequestCallBack callBack) {
			// TODO Auto-generated constructor stub
			this.callBack = callBack;
		}

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if (msg.what == SUCCESS) {
				String result = (String) msg.obj;
				callBack.onSuccess(result);
			} else if (msg.what == FAIL) {
				callBack.onFail();
			}

		}

		public void onSuccess(String result) {
			Message msg = new Message();
			msg.obj = result;
			msg.what = SUCCESS;
			this.sendMessage(msg);
		}

		public void onFail() {
			Message msg = new Message();
			msg.what = FAIL;
			this.sendEmptyMessage(FAIL);
		}

	}
}
