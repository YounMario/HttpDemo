package com.younchen.utils.http.handler;

import java.io.IOException;

import android.os.Handler;
import android.os.Message;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class HttpRequestHandler extends Handler implements Callback {

	private int SUCCESS = 0x01;
	private int FAIL = 0x02;
	private HttpCallBack callBack;

	public HttpRequestHandler(HttpCallBack callBack) {
		// TODO Auto-generated constructor stub
		this.callBack = callBack;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		if (msg.what == SUCCESS) {
			String t = (String) msg.obj;
			callBack.onSuccess(t);
		} else if (msg.what == FAIL) {
			String message = (String) msg.obj;
			callBack.onFail(message);
		}

	}

	public void sendSuccess(String body) {
		Message msg = new Message();
		msg.obj = body;
		msg.what = SUCCESS;
		this.sendMessage(msg);
	}

	public void sendFail(String str) {
		Message msg = new Message();
		msg.what = FAIL;
		msg.obj = str;
		this.sendEmptyMessage(FAIL);
	}

	@Override
	public void onResponse(Response arg0) throws IOException {
		// TODO Auto-generated method stub
		try {
			sendSuccess(arg0.body().string());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sendFail(e.getMessage());
		}
	}

	@Override
	public void onFailure(Request arg0, IOException arg1) {
		// TODO Auto-generated method stub
		try {
			sendFail(arg1.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static interface HttpCallBack {

		public void onSuccess(String body);

		public void onFail(String message);
	}

}
