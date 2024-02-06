package com.example.demo.mongodb;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "test")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class test_db {

    @Id
    private ObjectId id;

    private String title;

    private String data;

    @DBRef
    List<mongoChild> childs;

    public test_db(String title, String data) {
        this.title = title;
        this.data = data;
        this.childs = new ArrayList<mongoChild>();
    }
}
