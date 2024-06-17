package com.example.demo.WebFlux.r2dbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RequestMapping("/oracle")
@Controller
public class oracle_cointroller {
    
    @Autowired
    oracle_repository repo;
    
    @GetMapping("")
    public Flux<oracle_test> getAllData() {
        return repo.findAll();
    }
    
    @GetMapping("/{param}")
    public Mono<oracle_test> getData(@RequestParam int param) {
        return repo.findById(param);
    }

    @PostMapping("")
    public String insertData(@ModelAttribute oracle_test test) {
        repo.save(test);
        return "home";
    }
    
    @DeleteMapping("/{param}")
    public String deleteData(@RequestParam int param) {
        repo.deleteById(param);
        return "home";
    }

    @PutMapping("/{param}")
    public String updateData(@RequestParam int param, @ModelAttribute oracle_test test) {
        test.setId(param);
        repo.save(test);
        return "home";
    }
}
