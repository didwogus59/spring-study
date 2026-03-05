package com.example.demo.user;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Boolean login_session(User user) {
        Optional<User> loginUser = repository.findByName(user.getName());
        if(!loginUser.isPresent()) {
            return false;
        }
        String hashPassword = loginUser.get().getPassword();
        System.out.println(loginUser.get().getPassword());
        if(passwordEncoder.matches(user.getPassword(), hashPassword)) {
            System.out.println("user service login true");
            return true;
        }
        return false;
    }

    public Boolean create_user(User user) {
        Optional<User> check = repository.findByName(user.getName());
        if(!check.isPresent()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            repository.insert(user);
            return true;
        }
        return false;
    }

    public Boolean login_jwt(User user) {
        Optional<User> loginUser = repository.findByName(user.getName());
        if(!loginUser.isPresent()) {
            return false;
        }
        String hashPassword = loginUser.get().getPassword();
        System.out.println(loginUser.get().getPassword());
        if(passwordEncoder.matches(user.getPassword(), hashPassword)) {
            System.out.println("true");
            return true;
        }
        return false;
    }

    public Optional<User> getUserByName(String name) {
        return repository.findByName(name);
    }
}
