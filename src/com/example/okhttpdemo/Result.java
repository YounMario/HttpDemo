/**
 * @author kang
 * @date ${date}
 */
package com.example.okhttpdemo;

import java.io.Serializable;

/**
 * 反回结果类，父类。
 * 
 * @author kang
 * @date 2014年8月6日
 */
public class Result implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4092180799883572903L;

	public static final int RESULT_CODE_OK = 0;

	private int code=-1;

	protected String message="错误";

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return message;
	}

	public void setMsg(String msg) {
		this.message = msg;
	}

}
