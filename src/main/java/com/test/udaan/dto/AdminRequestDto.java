package com.test.udaan.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminRequestDto {

	@NotBlank(message = "User Id cannot be empty")
	private String userId;
	@NotBlank(message = "Admin Id cannot be empty")
	private String adminId;
	@NotBlank(message = "Result Id cannot be empty")
	private String result;

}
