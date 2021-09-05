package com.test.udaan.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="test")
public class Test {

	@Valid
	@NotEmpty(message = "Name cannot be empty")
	private String name;
}
