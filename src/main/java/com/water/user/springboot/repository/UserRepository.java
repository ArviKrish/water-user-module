package com.water.user.springboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodb.BasicDBObjectBuilder;
import com.water.user.springboot.document.Users;

public interface UserRepository extends MongoRepository<Users, Integer> {
}
