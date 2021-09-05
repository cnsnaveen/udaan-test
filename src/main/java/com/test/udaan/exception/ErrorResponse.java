package com.test.udaan.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

	private String status;
	private String reason;

	public ErrorResponse(String status, String reason) {
		this.status = status;
		this.reason = reason;
	}

}
