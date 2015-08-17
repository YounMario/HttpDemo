package com.younchen.utils.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import junit.framework.Assert;

import com.younchen.utils.http.handler.HttpRequestHandler;
import com.younchen.utils.http.thread.Excutor;

/**
 * 异步请求类
 * 
 * @author longquan
 * @param <T>
 *
 */
public class AsyncHttpRequest {

	public static final String GET = "GET";
	public static final String POST = "POST";

	private String url;
	private HashMap<String, String> header;
	private HashMap<String, String> params;
	private Excutor excutor;

	private String method;
	private List<FileDiscription> flist;
	private Builder builder;

	private AsyncHttpRequest() {
		excutor = Excutor.getInstance();
	}

	private AsyncHttpRequest(Builder builder) {
		this();
		this.url = builder.url;
		this.header = builder.header;
		this.params = builder.params;
		this.method = builder.method;
		this.flist = builder.flist;
		this.builder = builder;
		cheack(builder);
	}

	private void cheack(Builder builder) {
		// TODO Auto-generated method stub
		if (method == null)
			this.method = GET;
		if (builder.handler == null) {
			throw new IllegalArgumentException("http callback must not be null");
		}
		Assert.assertNotNull(builder.url);

	}

	public void execute() {
		// excutor.execute(new HandlerRunnable(new ));
		excutor.execute(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				HttpUtil.request(url, params, header, flist, builder.handler,
						method);
			}
		});
	}

	public static class Builder {

		private String url;
		private HashMap<String, String> header;
		private HashMap<String, String> params;
		private List<FileDiscription> flist;
		private String method;
		private HttpRequestHandler handler;

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

		public AsyncHttpRequest build() {
			return new AsyncHttpRequest(this);
		}

		public Builder callBack(HttpRequestHandler.HttpCallBack callBack) {
			// TODO Auto-generated method stub
			this.handler = new HttpRequestHandler(callBack);
			return this;
		}
		
		/**
		 * 获取builder
		 * @return
		 */
		public static Builder getBuilder(){
			return new Builder();
		}
	}

}
