package com.test.udaan.dto;

import java.util.List;

import com.test.udaan.domain.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelfAssesmentRequestDto {

	private String userId;
	private String name;
	private String pinCode;
	private String phoneNumber;
	private List<String> symptoms;
	private boolean travelHistory;
	private boolean contactWithCovidPatient;
	private Role role;
	private String result;
}
