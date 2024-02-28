package com.example.demo.async;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/async")
public class async_controller {
    
    @GetMapping("/test1")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
}
