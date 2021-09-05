package com.test.udaan.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.udaan.domain.Test;
import com.test.udaan.exception.GenericException;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	MongoTemplate mongoTemplate;
//	@GetMapping
//	public Test test() {
//		return new Test("Hello world");
//	}

//	@GetMapping("{name}")
//	public Test testByParam(@PathVariable String name) {
//		return new Test(name);
//	}
	
	@GetMapping
	public Test testByQueryParam(@RequestParam(name="id") String name) {
		return new Test(name);
	}

	@PostMapping("save")
	public ResponseEntity<?> saveTest(@Valid @RequestBody Test test) {
		try {
			mongoTemplate.save(test, "test");
			return new ResponseEntity<Test>(new Test(test.getName()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<GenericException>(new GenericException("FAILURE", e.getMessage()),
					HttpStatus.BAD_REQUEST);

		}
	}
}
