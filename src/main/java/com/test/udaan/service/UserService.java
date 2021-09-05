package com.test.udaan.service;

import com.test.udaan.domain.Role;
import com.test.udaan.dto.AdminReponseDto;
import com.test.udaan.dto.AdminRequestDto;
import com.test.udaan.dto.SelfAssesmentRequestDto;
import com.test.udaan.dto.UserDto;
import com.test.udaan.dto.UserRegisterResponseDto;
import com.test.udaan.dto.UserResponseDto;
import com.test.udaan.dto.ZoneDto;

public interface UserService {

	UserRegisterResponseDto registerUser(UserDto user, Role role);

	UserResponseDto selfAssessment(SelfAssesmentRequestDto user);

	AdminReponseDto updateCovidResult(AdminRequestDto request);

	ZoneDto getZoneInfo(String pinCode);

}
