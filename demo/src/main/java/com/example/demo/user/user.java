package com.example.demo.user;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;


import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Document(collection = "user")
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class user implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;
    
    private String name;

    private String password;

    private String role;

    private String email;
    
    private String provider;

    private String providerId;

    user(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = "user";
    }

    user(String name, String password) {
        this.name = name;
        this.password = password;
        this.email = "noEmail";
        this.role = "user";
    }
    
}
