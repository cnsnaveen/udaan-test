package com.test.udaan.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.test.udaan.domain.Role;
import com.test.udaan.domain.User;
import com.test.udaan.dto.AdminReponseDto;
import com.test.udaan.dto.AdminRequestDto;
import com.test.udaan.dto.SelfAssesmentRequestDto;
import com.test.udaan.dto.UserDto;
import com.test.udaan.dto.UserRegisterResponseDto;
import com.test.udaan.dto.UserResponseDto;
import com.test.udaan.dto.ZoneDto;
import com.test.udaan.exception.GenericException;
import com.test.udaan.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	MongoTemplate mongoTemplate;

	UserRepository userRepo;

	UserServiceImpl(MongoTemplate mongoTemplate, UserRepository userRepo) {
		this.mongoTemplate = mongoTemplate;
		this.userRepo = userRepo;
	}

	@Override
	public UserRegisterResponseDto registerUser(UserDto requetsUser, Role role) {
		try {
			User user = new User();

			BeanUtils.copyProperties(requetsUser, user);

			user.setRole(role);
			user = mongoTemplate.insert(user, "user");

			return new UserRegisterResponseDto(user.getId());
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public UserResponseDto selfAssessment(SelfAssesmentRequestDto user) {
		try {
			UserResponseDto userResponse = new UserResponseDto();

			if (!userRepo.existsById(user.getUserId())) {
				throw new GenericException(HttpStatus.BAD_REQUEST.toString(),
						String.format("Invalid User Id", user.getUserId()));
			}
			userResponse.setRiskPercentage(String.format("%s", calculateRiskPercentage(user)));
			return userResponse;
		} catch (Exception e) {
			throw e;
		}
	}

	private int calculateRiskPercentage(SelfAssesmentRequestDto user) {

		if (user.getSymptoms().size() > 2 && (user.isTravelHistory() || user.isContactWithCovidPatient())) {
			return 95;
		}
		if (user.getSymptoms().size() == 2 && (user.isTravelHistory() || user.isContactWithCovidPatient())) {
			return 75;
		}
		if (user.getSymptoms().size() == 1 && (user.isTravelHistory() || user.isContactWithCovidPatient())) {
			return 50;
		}
		if (user.getSymptoms().size() == 0 && !user.isTravelHistory() && !user.isContactWithCovidPatient()) {
			return 5;
		}
		return 5;
	}

	@Override
	public AdminReponseDto updateCovidResult(AdminRequestDto request) {
		try {

			if (userRepo.findByIdAndRole(request.getAdminId(), Role.ADMIN) == null) {
				throw new GenericException(HttpStatus.BAD_REQUEST.toString(), "Admin id not found");
			}

			Optional<User> user = userRepo.findById(request.getUserId());
			if (user.isEmpty()) {
				throw new GenericException(HttpStatus.BAD_REQUEST.toString(),
						String.format("Invalid User Id", request.getUserId()));
			}

			user.get().setResult(request.getResult());

			User updatedUser = mongoTemplate.save(user.get());
			if (updatedUser == null) {
				throw new GenericException(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
						String.format("Unable to update user", request.getUserId()));
			}

			return new AdminReponseDto(true);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public ZoneDto getZoneInfo(String pinCode) {
		try {
			List<User> users = userRepo.findByPinCodeAndResultAndRole(pinCode, "positive", Role.USER);
			ZoneDto zoneDto = new ZoneDto();

			zoneDto.setNumCases(users.size());
			zoneDto.setZoneType(users.size() > 5 ? "RED" : (users.size() > 0 && users.size() <= 5) ? "ORANGE" : "GREEN");

			return zoneDto;
		} catch (Exception e) {
			throw e;
		}
	}

}
