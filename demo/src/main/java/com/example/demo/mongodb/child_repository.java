package com.example.demo.mongodb;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface child_repository extends MongoRepository<mongoChild, ObjectId> {
    
}
