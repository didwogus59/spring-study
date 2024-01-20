package com.example.demo.mongodb_test;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("child")
public class mongoChild {
    
    @Id
    private ObjectId id;

    private String data;

    public mongoChild(String data) {
        this.data = data;
    }
}
