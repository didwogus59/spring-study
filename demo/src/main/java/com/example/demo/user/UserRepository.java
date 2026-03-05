package com.example.demo.user;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;


public interface UserRepository extends MongoRepository<User, ObjectId>  {
    Optional<User> findByName(String name);
    Optional<User> findOneByProviderIdAndProvider(String providerId, String provider);
}
