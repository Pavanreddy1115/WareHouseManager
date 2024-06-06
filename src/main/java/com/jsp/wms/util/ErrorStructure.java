package com.jsp.wms.util;

import lombok.Getter;

@Getter
public class ErrorStructure {
	private int statuscode;
	private String errorMessage;
	private String rootCause;

	public ErrorStructure setStatuscode(int statuscode) {
		this.statuscode = statuscode;
		return this;
	}

	public ErrorStructure setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}

	public ErrorStructure setRootCause(String rootCause) {
		this.rootCause = rootCause;
		return this;
	}

}
