package com.example.demo.mongodb_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class db_serviceTest {

    @Autowired
    child_repository repoC;

    @Autowired
    db_repository repo;

    @Autowired
    db_service service;
    @Test
    void testDelete_child() {
        test_db test = repo.save(new test_db("test", "data"));
        mongoChild child = service.create_child2(test.getId(), "child");
        service.create_child2(test.getId(), "chil23");
        service.create_child2(test.getId(), "asgasg23");
        service.delete_child(test.getId(), child.getId());
    }
}
