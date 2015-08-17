package com.younchen.utils.http;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.younchen.utils.http.CustomRequestBody.UpLoadListener;

/**
 * see also https://github.com/square/okhttp/wiki/Recipes
 * 
 * @author longquan
 *
 */
public class HttpUtil {

	private final static OkHttpClient client = new OkHttpClient();

	public static void request(String url, Map<String, String> params,
			Map<String, String> header, List<FileDiscription> flist,
			Callback callBack, String method) {
		if (method.equals("POST")) {
			if (flist.size() > 0) {
				uploadMultiFile(url, params, flist, header, callBack);
			} else {
				post(url, params, header, callBack);
			}

		} else if (method.equals("GET")) {
			get(url, params, header, callBack);
		}
	}

	/**
	 * get 请求
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            参数
	 * @param header
	 *            头部信息
	 * @return
	 */
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

	/**
	 * 带回调的get请求
	 * 
	 * @param url
	 *            地址
	 * @param params
	 *            参数
	 * @param header
	 *            头部
	 * @param callBack
	 */
	public static void get(String url, Map<String, String> params,
			Map<String, String> header, Callback callBack) {
		url = bindGetRequestParam(url, params);
		Request.Builder requestBuilder = new Request.Builder().url(url);
		bindHeader(requestBuilder, header);
		client.newCall(requestBuilder.build()).enqueue(callBack);
	}

	/**
	 * Post请求
	 * 
	 * @param url
	 *            地址
	 * @param params
	 *            参数
	 * @param header
	 *            头部
	 * @return
	 */
	public static void post(String url, Map<String, String> params,
			Map<String, String> header, Callback callBack) {
		Request.Builder requestBuilder = new Request.Builder();
		bindPostRequestPrams(requestBuilder, params);
		bindHeader(requestBuilder, header);
		Request request = requestBuilder.url(url).build();
		client.newCall(request).enqueue(callBack);

	}

	/**
	 * 上传单文件表单
	 * 
	 * @param url
	 * @param params
	 * @param discription
	 * @param header
	 * @return
	 */
	public static void upLoad(String url, Map<String, String> params,
			FileDiscription discription, Map<String, String> header,
			Callback callBack) {
		Request.Builder requestBuilder = new Request.Builder();
		MultipartBuilder multipartBuilder = new MultipartBuilder();
		multipartBuilder.type(MultipartBuilder.FORM);
		bindHeader(requestBuilder, header);
		bindMulitPartParam(multipartBuilder, params);
		bindFilePart(multipartBuilder, discription, null);
		Request request = requestBuilder.url(url)
				.post(multipartBuilder.build()).build();
		client.newCall(request).enqueue(callBack);

	}

	/**
	 * 多文件表单上传
	 * 
	 * @param url
	 * @param params
	 * @param discriptions
	 * @param header
	 * @return
	 */
	public static void uploadMultiFile(String url, Map<String, String> params,
			List<FileDiscription> discriptions, Map<String, String> header,
			Callback callBack) {
		Request.Builder requestBuilder = new Request.Builder();
		MultipartBuilder multipartBuilder = new MultipartBuilder();
		multipartBuilder.type(MultipartBuilder.FORM);
		bindHeader(requestBuilder, header);
		bindMulitPartParam(multipartBuilder, params);
		bindMulitPartFile(multipartBuilder, discriptions);
		Request request = requestBuilder.url(url)
				.post(multipartBuilder.build()).build();
		client.newCall(request).enqueue(callBack);
	}

	public static String uploadMultiFileWithPrograss(String url,
			Map<String, String> params, List<FileDiscription> discriptions,
			Map<String, String> header) {
		Request.Builder requestBuilder = new Request.Builder();
		MultipartBuilder multipartBuilder = new MultipartBuilder();
		multipartBuilder.type(MultipartBuilder.FORM);
		bindHeader(requestBuilder, header);
		bindMulitPartParam(multipartBuilder, params);
		bindMulitPartFile(multipartBuilder, discriptions);
		Request request = requestBuilder.url(url)
				.post(multipartBuilder.build()).build();
		Response response = null;
		try {
			response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				return response.body().string();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 文件下载
	 */
	public void downLoad() {

	}

	/**
	 * 绑定 多表单参数
	 * 
	 * @param multipartBuilder
	 * @param params
	 */
	private static void bindMulitPartParam(MultipartBuilder multipartBuilder,
			Map<String, String> params) {
		// TODO Auto-generated method stub
		if (params == null)
			return;
		Set<String> set = params.keySet();
		for (String key : set) {
			String value = params.get(key);
			if (value == null)
				value = "";
			multipartBuilder.addFormDataPart(key, value);
		}
	}

	/**
	 * 绑定多个文件
	 * 
	 * @param multipartBuilder
	 * @param fileList
	 */
	private static void bindMulitPartFile(MultipartBuilder multipartBuilder,
			List<FileDiscription> fileList) {
		if (fileList == null)
			return;
		for (FileDiscription fileDiscription : fileList) {
			bindFilePart(multipartBuilder, fileDiscription,
					fileDiscription.getUploadPrograssListener());
		}
	}

	/**
	 * 绑定文件
	 * 
	 * @param multipartBuilder
	 * @param key
	 * @param file
	 */
	private static void bindFilePart(MultipartBuilder multipartBuilder,
			FileDiscription fileDiscription,UpLoadListener listener) {
		// TODO Auto-generated method stub
		multipartBuilder
				.addFormDataPart(
						fileDiscription.getKey(),
						fileDiscription.getFileName(),
						new CustomRequestBody(
								RequestBody.create(MediaType
										.parse(fileDiscription.getMediaType()),
										fileDiscription.getFile()), listener))
				.type(MultipartBuilder.FORM).build();
	}

	/**
	 * 绑定post参数
	 * 
	 * @param builder
	 * @param params
	 */
	private static void bindPostRequestPrams(Request.Builder builder,
			Map<String, String> params) {
		FormEncodingBuilder formBodyBuilder = new FormEncodingBuilder();
		if (params == null)
			return;
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			String parameValue = params.get(key);
			formBodyBuilder.add(key, parameValue);
		}
		builder.post(formBodyBuilder.build());
	}

	/**
	 * 
	 * @param builder
	 *            request 构造器
	 * @param header
	 *            http头部
	 */
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

	/**
	 * 绑定get请求参数
	 * 
	 * @param url
	 *            地址
	 * @param param
	 *            参数
	 * @return
	 */
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
