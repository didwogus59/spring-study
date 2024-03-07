package com.example.demo.async.mysql;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/asyncdb")
public class async_db_controller {
    
    @GetMapping("/test1")
    @ResponseBody
    public String getMethodName() {
        return "123";
    }
    
}
