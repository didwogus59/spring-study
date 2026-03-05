package com.example.demo.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.exception.UserNotFoundException;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Boolean loginSession(User user) {
        Optional<User> loginUser = userRepository.findByName(user.getName());
        if (!loginUser.isPresent()) {
            return false;
        }
        String hashPassword = loginUser.get().getPassword();
        if (passwordEncoder.matches(user.getPassword(), hashPassword)) {
            return true;
        }
        return false;
    }

    public Boolean createUser(User user) {
        Optional<User> check = userRepository.findByName(user.getName());
        if (!check.isPresent()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole("ROLE_USER");
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public Boolean loginJwt(User user) {
        Optional<User> loginUser = userRepository.findByName(user.getName());
        if (!loginUser.isPresent()) {
            // Return false instead of throwing exception for security reasons
            return false;
        }
        String hashPassword = loginUser.get().getPassword();
        if (passwordEncoder.matches(user.getPassword(), hashPassword)) {
            return true;
        }
        return false;
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + name));
    }
}
