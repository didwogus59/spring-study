package com.example.demo.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class redis_repositoryTest {

    @Autowired
    private redis_repository repo;

    @Test
    void test() {
        String test = "test";
        redis_token token = new redis_token(test, -1);

        repo.save(token);

        repo.count();

    }

}
