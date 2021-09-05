package com.test.udaan.domain;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "user")
public class User {

	@Id
	private String id;
	private String name;
	private String pinCode;
	private String phoneNumber;
	private List<String> symptoms;
	private boolean travelHistory;
	private boolean contactWithCovidPatient;
	private Role role;
	private String result;

	public User() {
		id = id == null ? ObjectId.get().toString() : id;
	}
}
