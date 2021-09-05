package com.test.udaan.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.test.udaan.domain.Role;
import com.test.udaan.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	User findByIdAndRole(String id, Role role);

	List<User> findByResultAndRole(String result, Role role);
}
