package com.example.demo.user;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class user_service {
    
    @Autowired
    private user_repository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Boolean login(user user) {
        Optional<user> login_user = repository.findByName(user.getName());
        String hash_password = login_user.get().getPassword();
        if(!login_user.isPresent()) {
            return false;
        }
        System.out.println(login_user.get().getPassword());
        if(passwordEncoder.matches(user.getPassword(), hash_password)) {
            System.out.println("true");
            return true;
        }
        return false;
    }

    public Boolean create_user(user user) {
        Optional<user> check = repository.findByName(user.getName());
        if(!check.isPresent()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            repository.insert(user);
            return true;
        }
        return false;
    }
}
