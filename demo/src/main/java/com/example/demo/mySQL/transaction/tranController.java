package com.example.demo.mySQL.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RestController
@RequestMapping("/mysql/tran")
public class tranController {
    @Autowired
    tranService service;


    @GetMapping("/test")
    public String getMethodName(@RequestParam String name){
        try {
            service.test(name);
            return name;
        
        } catch (Exception e){
            return "input is error";
        }
        
    }
    
}
