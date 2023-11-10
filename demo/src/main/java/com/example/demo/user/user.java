package com.example.demo.user;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;


import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Document(collection = "user")
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class user {
    @Id
    private ObjectId id;

    private String name;

    private String password;

    user(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
