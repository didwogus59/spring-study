package com.example.demo.user;

import java.util.Optional;

import org.bson.types.ObjectId;

public interface UserRepositoryInterface {
    Optional<User> findByName(String name);
    Optional<User> findOneByProviderIdAndProvider(String providerId, String provider);
    User save(User user);
    void delete(User user);
    Iterable<User> findAll();
}