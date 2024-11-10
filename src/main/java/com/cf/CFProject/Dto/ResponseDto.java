package com.cf.CFProject.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDto {

	private Integer id;
	private String username;
	private String email;
	private Long phone;

}
