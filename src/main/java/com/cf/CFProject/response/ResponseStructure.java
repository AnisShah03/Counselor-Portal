package com.cf.CFProject.response;

import lombok.Data;

@Data
public class ResponseStructure<T> {

	private Integer status;
	private String message;
	private T data;

}
