package com.example.demo.mongodb_test;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "test")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class test_db {

    @Id
    private ObjectId id;

    private String title;

    private String data;

    public test_db(String title, String data) {
        this.title = title;
        this.data = data;
    }

}
