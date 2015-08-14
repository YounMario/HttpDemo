package com.example.okhttpdemo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.younchen.utils.http.FileDiscription;
import com.younchen.utils.http.HttpUtil;
import com.younchen.utils.http.thread.Excutor;
import com.younchen.utils.http.thread.HandlerRunnable;
import com.younchen.utils.http.thread.HandlerRunnable.Listenner;

public class RequestAction {

	private Excutor excutor;

	public RequestAction() {
		excutor = new Excutor();
	}

	public void uploadFile(final HashMap<String, String> params, final File file) {
		final String url = "http://192.168.1.41:8080/titan/Upload/file";

		excutor.execute(new HandlerRunnable(new Listenner() {

			@Override
			public void onPostExecute(Serializable result) {
				// TODO Auto-generated method stub

			}

			@Override
			public Serializable doInBackground() {
				// TODO Auto-generated method stub
				FileDiscription discriptions = new FileDiscription(file);
				HttpUtil.upLoad(url, params, discriptions, null,
						new Callback() {

							@Override
							public void onResponse(Response response)
									throws IOException {
								// TODO Auto-generated method stub
								if (response.isSuccessful()) {
									System.out.println("success");
								}
							}

							@Override
							public void onFailure(Request arg0, IOException arg1) {
								// TODO Auto-generated method stub

							}
						});
				return null;
			}
		}));

	}
}
