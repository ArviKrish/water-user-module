package com.water.user.springboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.water.user.springboot.document.Users;
import com.water.user.springboot.document.WahterUsers;

@Repository
public interface WahterUsersRepository extends MongoRepository<WahterUsers, String>, WahterUsersRepositoryCustom {
	
	public WahterUsers findByPhoneNumber(String phoneNumber);

	
}
