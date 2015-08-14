package com.example.okhttpdemo;

import java.io.File;
import java.util.Date;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.younchen.utils.FileUtil;
import com.younchen.utils.ToastUtil;
import com.younchen.utils.http.AsyncHttpRequest;
import com.younchen.utils.http.AsyncHttpRequest.Builder;
import com.younchen.utils.http.AsyncHttpRequest.RequestCallBack;
import com.younchen.utils.http.FileDiscription;
import com.younchen.utils.http.HttpUtil;

public class MainActivity extends ActionBarActivity {

	private File cameraFile;
	private final int REQUEST_CODE_LOCAL = 201;
	private final int REQUEST_CODE_CAMERA = 202;

	private TextView textView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.txt_file);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub

		AsyncHttpRequest.Builder builder = new AsyncHttpRequest.Builder();
		builder.addParams("hehe", "haha").url("http://www.baidu.com")
				.method(AsyncHttpRequest.GET).callBack(new RequestCallBack() {

					@Override
					public void onSuccess(String result) {
						// TODO Auto-generated method stub
						textView.setText(result);
					}

					@Override
					public void onFail() {
						// TODO Auto-generated method stub

					}
				}).build().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void selectPicFromCamera() {
		if (!FileUtil.getSDCardMount()) {
			ToastUtil.getInstance(this).show("SD卡不存在，不能拍照");
			return;
		}
		String tempImgDir = FileUtil.IMAGE_URL + "/" + new Date().getTime()
				+ ".jpg";
		cameraFile = new File(tempImgDir);
		startActivityForResult(
				new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(
						MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraFile)),
				REQUEST_CODE_CAMERA);
	}

	/**
	 * 从图库获取图片
	 */
	public void selectPicFromLocal() {
		Intent intent;
		if (Build.VERSION.SDK_INT < 19) {
			intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType("image/*");

		} else {
			intent = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		}
		startActivityForResult(intent, REQUEST_CODE_LOCAL);
	}

	/**
	 * onActivityResult
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		try {

			if (requestCode == REQUEST_CODE_LOCAL) { // 发送本地图片
				if (data != null) {
					Uri selectedImage = data.getData();
					if (selectedImage != null) {
						sendPicByUri(selectedImage);
					}
				}
			} else if (requestCode == REQUEST_CODE_CAMERA) { // 发送照片

				if (cameraFile != null && cameraFile.exists())
					uploadImage(cameraFile.getAbsolutePath());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * 根据图库图片uri发送图片
	 * 
	 * @param selectedImage
	 */
	private void sendPicByUri(Uri selectedImage) {

		Cursor cursor = getContentResolver().query(selectedImage, null, null,
				null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			int columnIndex = cursor.getColumnIndex("_data");
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
			cursor = null;

			if (picturePath == null || picturePath.equals("null")) {
				ToastUtil.getInstance(this).show("找不到图片");
				return;
			}
			textView.setText(picturePath);
		} else {
			File file = new File(selectedImage.getPath());
			if (!file.exists()) {
				ToastUtil.getInstance(this).show("找不到图片");
				return;
			}
			textView.setText(file.getAbsolutePath());
		}

	}

	/**
	 * 发送图片
	 * 
	 * @param filePath
	 */
	private void uploadImage(final String filePath) {

	}

	public void upload(View v) {
		if (!TextUtils.isEmpty(textView.getText())) {
			File file = new File(textView.getText().toString());
			final String url = "http://192.168.1.41:8080/titan/Upload/file";
			
			
			AsyncHttpRequest.Builder builder=new AsyncHttpRequest.Builder();
			builder.addFile(new FileDiscription(file)).method(AsyncHttpRequest.POST).url(url).callBack(new RequestCallBack() {
				
				@Override
				public void onSuccess(String result) {
					// TODO Auto-generated method stub
					textView.setText(result);
				}
				
				@Override
				public void onFail() {
					// TODO Auto-generated method stub
					
				}
			}).build().execute();
		 
		}
	}

	public void selectPic(View v) {
		selectPicFromLocal();
	}

}
