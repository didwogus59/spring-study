package com.example.demo.imageHandling;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.mySQL.mySQLService;

@SpringBootTest
public class imageRepositoryTest {

    @Autowired
    imageRepository repo;

    @Autowired
    mySQLService service;
    @Test
    void testTest_repo() {
    }
}
