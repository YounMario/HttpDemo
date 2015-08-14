package com.younchen.utils.http.thread;

import java.io.Serializable;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * @author longquan
 * @date 2015年3月31日
 * @description
 */
public class HandlerRunnable implements Runnable {

	public static interface Listenner {// 异步任务接口
		public Serializable doInBackground();// 后台执行线程

		public void onPostExecute(Serializable result);// ui线程上执行
	}

	// *******************************************
	protected Listenner listenner;

	final Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Bundle bun = msg.getData();
			listenner.onPostExecute(bun.getSerializable("serializable"));
		}
	};
	
	public HandlerRunnable(){
		
	}

	public HandlerRunnable(Listenner listenner) {
		this.listenner = listenner;
	}

	public void cancel() {

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Serializable ser = listenner.doInBackground();
		Message msg = Message.obtain();
		Bundle bun = new Bundle();
		bun.putSerializable("serializable", ser);
		msg.setData(bun);
		handler.sendMessage(msg);
	}

}
