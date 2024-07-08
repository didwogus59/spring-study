package com.example.demo.mySQL.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/mysql/tran")
public class tranController {
    @Autowired
    tranService service;


    @GetMapping("/test")
    public String getMethodName() throws Exception {
        
        service.test();

        return "home";
    }
    
}
