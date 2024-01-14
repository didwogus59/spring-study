package com.example.demo.mongodb_test;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface db_repository extends MongoRepository<test_db, ObjectId> {
    
} 