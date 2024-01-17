package com.example.demo.mongodb_test;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("child")
public class child {
    
    @Id
    private ObjectId id;

    private String data;

    public child(String data) {
        this.data = data;
    }
}
