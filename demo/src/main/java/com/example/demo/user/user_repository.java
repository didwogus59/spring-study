package com.example.demo.user;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;


public interface user_repository extends MongoRepository<user, ObjectId>  {
    Optional<user> findByName(String name);
    Optional<user> findOneByProviderIdAndProvider(String providerId, String provider);
}
