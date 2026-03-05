package com.example.demo.user;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Document(collection = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{
    @Id
    private ObjectId id;

    private String name;

    private String password;

    private String role;

    private String email;

    private String provider;

    private String providerId;

    // Constructors with proper encapsulation
    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = "ROLE_USER";
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.email = "noEmail";
        this.role = "ROLE_USER";
    }
    
    // Getters and setters are provided through @Data annotation
}
