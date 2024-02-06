package com.example.demo.rest_api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/json")
public class otherServer {
    
    @GetMapping("/data")
    public MyData getData() {
        MyData data = new MyData();
        data.setFrom("java");
        data.setData("i am spring boot");
        return data;
    }
}
