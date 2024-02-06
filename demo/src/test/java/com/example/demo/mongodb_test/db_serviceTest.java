package com.example.demo.mongodb_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.mongodb.child_repository;
import com.example.demo.mongodb.db_repository;
import com.example.demo.mongodb.db_service;
import com.example.demo.mongodb.mongoChild;
import com.example.demo.mongodb.test_db;

@SpringBootTest
public class db_serviceTest {

    @Autowired
    child_repository repoC;

    @Autowired
    db_repository repo;

    @Autowired
    db_service service;
    @Test
    void update_data3() {
        test_db test = repo.save(new test_db("test", "data"));
        mongoChild child = service.create_child2(test.getId(), "child");
        service.create_child2(test.getId(), "chil23");
        service.create_child2(test.getId(), "asgasg23");
        service.delete_child(test.getId(), child.getId());
    }
}
