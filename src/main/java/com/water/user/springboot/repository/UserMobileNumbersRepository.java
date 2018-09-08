package com.water.user.springboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.water.user.springboot.document.UserMobileNumbers;

@Repository
public interface UserMobileNumbersRepository extends MongoRepository<UserMobileNumbers, String>, UserMobileNumbersRepositoryCustom {

}
