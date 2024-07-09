package com.example.demo.mySQL.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class tranService {
    
    @Autowired
    tranRepository repo;


    @Transactional(transactionManager = "myTran")
    public void test(String name) {

        repo.save(new tranTest(name));
        if(name.equals("error")) {
            log.info(name);
            throw new RuntimeException("testing");
        }
    }
}
