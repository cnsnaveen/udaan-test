package com.test.udaan.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class GenericException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String status;
	private String reason;

	public GenericException() {
		super();
	}

	public GenericException(String status, String reason) {
		this.status = status;
		this.reason = reason;
	}

}
