package com.test.udaan.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.udaan.domain.Role;
import com.test.udaan.dto.SelfAssesmentRequestDto;
import com.test.udaan.dto.UserDto;
import com.test.udaan.dto.UserRegisterResponseDto;
import com.test.udaan.dto.UserResponseDto;
import com.test.udaan.exception.GenericException;
import com.test.udaan.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private UserService userService;

	UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("registerUser")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto user) {
		try {
			UserRegisterResponseDto registeredUser = userService.registerUser(user, Role.USER);
			return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
		} catch (GenericException e) {
			return new ResponseEntity<GenericException>(
					new GenericException(HttpStatus.BAD_REQUEST.toString(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("selfAssessment")
	public ResponseEntity<?> selfAssessment(@Valid @RequestBody SelfAssesmentRequestDto user) {
		try {
			UserResponseDto userResponse = userService.selfAssessment(user);
			return new ResponseEntity<>(userResponse, HttpStatus.OK);
		} catch (GenericException e) {
			return new ResponseEntity<GenericException>(
					new GenericException(HttpStatus.BAD_REQUEST.toString(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
}
