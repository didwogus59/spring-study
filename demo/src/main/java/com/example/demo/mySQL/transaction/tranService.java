package com.example.demo.mySQL.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class tranService {
    
    @Autowired
    tranRepository repo;


    @Transactional
    public void test() {

        repo.save(new tranTest("test"));
        
        throw new RuntimeException("testing");
    }
}
