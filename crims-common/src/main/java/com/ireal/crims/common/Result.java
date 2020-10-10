package com.ireal.crims.common;


public class Result<T> {

	/**
	 * 0:成功
	 * 1：失败

	 */
	private String code = "0";
	
	private String msg;
	
	private T result;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
}
