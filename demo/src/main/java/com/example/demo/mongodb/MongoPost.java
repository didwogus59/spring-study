package com.example.demo.mongodb;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MongoPost {

    @Id
    private ObjectId id;

    private String title;

    private String content;

    @DBRef
    private List<mongoChild> children = new ArrayList<>();

    public MongoPost(String title, String content) {
        this.title = title;
        this.content = content;
        this.children = new ArrayList<>();
    }
}
