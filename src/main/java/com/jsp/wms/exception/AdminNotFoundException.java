package com.jsp.wms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AdminNotFoundException extends RuntimeException {
	private String message;
}
