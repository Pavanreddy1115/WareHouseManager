package com.jsp.wms.util;

public class ResponseStructure<T> {
	private int statuscode;
	private String message;
	private T data;

	public ResponseStructure<T> setStatuscode(int statuscode) {
		this.statuscode = statuscode;
		return this;
	}

	public ResponseStructure<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	public ResponseStructure<T> setData(T data) {
		this.data = data;
		return this;
	}

	public int getStatuscode() {
		return statuscode;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}


}
