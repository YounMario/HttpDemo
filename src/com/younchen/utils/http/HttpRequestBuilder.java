package com.younchen.utils.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

public class HttpRequestBuilder {

	Request.Builder requestBuilder;
	private HashMap<String, String> params;
	private HttpMethod method;
	private String url;
	private boolean hasFile;
	private List<FileDiscription> fileList;
	

	public HttpRequestBuilder() {
		params = new HashMap<String, String>();
		requestBuilder = new Request.Builder();
		hasFile = false;
		fileList = new ArrayList<FileDiscription>();
	}

	/**
	 * 地址
	 * 
	 * @param url
	 *            网址
	 * @return
	 */
	public HttpRequestBuilder url(String url) {
		this.url = url;
		return this;
	}

	/**
	 * 添加请求头部
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public HttpRequestBuilder addHeader(String key, String value) {
		requestBuilder.addHeader(key, value);
		return this;
	}

	public HttpRequestBuilder addFile(FileDiscription file) {
		fileList.add(file);
		hasFile = true;
		return this;
	}

	/**
	 * 添加请求参数
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public HttpRequestBuilder addParams(String key, String value) {
		params.put(key, value);
		return this;
	}
	

	/**
	 * 添加http方法
	 * 
	 * @param method
	 * @return
	 */
	public HttpRequestBuilder method(HttpMethod method) {
		this.method = method;
		return this;
	}

	public Request build() {
		if (this.url == null)
			throw new IllegalArgumentException("url is null");
		if (this.method == null)
			this.method = HttpMethod.GET;

		if (this.method.name().equals(HttpMethod.GET.name())) {
			this.url = bindGetRequestParam(url, params);
		} else if (this.method.equals((HttpMethod.POST))) {
			if (!hasFile) {
				bindPostRequestPrams(requestBuilder, params);
			} else {
				MultipartBuilder multipartBuilder = new MultipartBuilder();
				multipartBuilder.type(MultipartBuilder.FORM);
				for (FileDiscription file : fileList) {
					bindFilePart(multipartBuilder, file);
				}
				bindMulitPartParam(multipartBuilder, params);
				requestBuilder.post(multipartBuilder.build());
			}
		}
		return requestBuilder.url(url).build();
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
	 * 绑定文件
	 * 
	 * @param multipartBuilder
	 * @param key
	 * @param file
	 */
	private static void bindFilePart(MultipartBuilder multipartBuilder,
			FileDiscription fileDiscription) {
		// TODO Auto-generated method stub
		multipartBuilder
				.addFormDataPart(
						fileDiscription.getKey(),
						fileDiscription.getFileName(),
						new CustomRequestBody(
								RequestBody.create(MediaType
										.parse(fileDiscription.getMediaType()),
										fileDiscription.getFile()),
								fileDiscription.getUploadPrograssListener()))
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
		if (params == null || params.size() == 0)
			return;
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			String parameValue = params.get(key);
			formBodyBuilder.add(key, parameValue);
		}
		builder.post(formBodyBuilder.build());
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

	public enum HttpMethod {
		GET, POST, DELETE, PUT;
	}

}
