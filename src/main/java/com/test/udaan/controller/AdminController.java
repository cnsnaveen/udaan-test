package com.test.udaan.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.udaan.domain.Role;
import com.test.udaan.dto.AdminRegisterResponseDto;
import com.test.udaan.dto.AdminRequestDto;
import com.test.udaan.dto.UserDto;
import com.test.udaan.exception.GenericException;
import com.test.udaan.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserService userService;

	@PostMapping("registerAdmin")
	public ResponseEntity<?> registerAdmin(@Valid @RequestBody UserDto user) {
		try {
			AdminRegisterResponseDto registeredUser = new AdminRegisterResponseDto(
					userService.registerUser(user, Role.ADMIN).getUserId());
			return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
		} catch (GenericException e) {
			return new ResponseEntity<GenericException>(
					new GenericException(HttpStatus.BAD_REQUEST.toString(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("updateCovidResult")
	public ResponseEntity<?> updateCovidResult(@Valid @RequestBody AdminRequestDto request) {
		try {

			return new ResponseEntity<>(userService.updateCovidResult(request), HttpStatus.CREATED);
		} catch (GenericException e) {
			return new ResponseEntity<GenericException>(
					new GenericException(HttpStatus.BAD_REQUEST.toString(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("getZoneInfo/{pincode}")
	public ResponseEntity<?> getZoneInfo(@PathVariable String pinCode) {
		try {

			return new ResponseEntity<>(userService.getZoneInfo(pinCode), HttpStatus.OK);
		} catch (GenericException e) {
			return new ResponseEntity<GenericException>(
					new GenericException(HttpStatus.BAD_REQUEST.toString(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
}
