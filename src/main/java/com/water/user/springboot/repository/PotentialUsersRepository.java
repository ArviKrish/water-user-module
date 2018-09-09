package com.water.user.springboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.water.user.springboot.document.PotentialUsers;
import com.water.user.springboot.document.Users;
import com.water.user.springboot.document.WahterUsers;

@Repository
public interface PotentialUsersRepository extends MongoRepository<PotentialUsers, String>, PotentialUsersRepositoryCustom {
	
	public PotentialUsers findByPhoneNumber(String phoneNumber);

	
}
