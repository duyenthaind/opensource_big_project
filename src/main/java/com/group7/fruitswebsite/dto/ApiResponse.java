package com.group7.fruitswebsite.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
	private int status;
	private String datetime;
	private String message;
	private Object result;

	public ApiResponse(int status, String datetime, String message, Object result) {
		super();
		this.status = status;
		this.datetime = datetime;
		this.message = message;
		this.result = result;
	}

	public ApiResponse() {
		super();
	}
}
