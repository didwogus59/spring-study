package com.example.demo.mySQL;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/mysql/rest")
public class my_rest_controller {
    
    @Autowired
    mySQLService service;

    
    @RequestMapping(path ="/{id}", method=RequestMethod.GET)
    public sqlEntity get_one_api() {
        return service.all_data().get(0);
    }

    @RequestMapping(path = "/", method=RequestMethod.GET)
    public List<sqlEntity> get_api() {
        return service.all_data();
    }

    @RequestMapping(path = "/get", method=RequestMethod.GET)
    public String get_api1() {
        ObjectMapper mapper = new ObjectMapper();
        String JsonArray = new String();
        try {
            JsonArray = mapper.writeValueAsString(service.all_data());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return JsonArray;
    }


    @RequestMapping(path ="/", method=RequestMethod.POST)
    public String post_api(@RequestParam String param) {
        return new String();
    }


    @RequestMapping(path ="/{id}", method=RequestMethod.PATCH)
    public String patch_api(@RequestParam String param) {
        return new String();
    }

    @RequestMapping(path ="/{id}", method=RequestMethod.PUT)
    public String put_api(@RequestParam String param) {
        return new String();
    }

    @RequestMapping(path ="/{id}", method=RequestMethod.DELETE)
    public String delete_api(@RequestParam String param) {
        return new String();
    }
}
