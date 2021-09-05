package com.test.udaan.dto;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.test.udaan.domain.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

	private String id;
	@NotEmpty(message = "Name cannot be empty")
	private String name;
	@NotEmpty(message = "Pin Code cannot be empty")
//	@Min(6)
//	@Max(6)
	private String pinCode;
//	@Min(10)
//	@Max(10)
	private String phoneNumber;
	private List<String> symptoms;
	private boolean travelHistory;
	private boolean contactWithCovidPatient;
	private Role role;
	private String result;
}
