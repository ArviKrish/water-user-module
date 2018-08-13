package com.water.user.springboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.water.user.springboot.document.Users;

@Repository
public interface UsersRepository extends MongoRepository<Users, String>, UsersRepositoryCustom {
	
	public Users findByPhoneNumber(String phoneNumber);
}
