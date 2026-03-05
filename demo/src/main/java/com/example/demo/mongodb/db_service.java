package com.example.demo.mongodb;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.mongodb.MongoPost;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transaction;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@Service
public class db_service {
    @Autowired
    private db_repository repository;

    @Autowired
    private child_repository repositoryC;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<MongoPost> all_data() {
        return repository.findAll();
    }


    public List<MongoPost> all_data2() {
        return mongoTemplate.findAll(MongoPost.class);
    }

    public Optional<MongoPost> get_data(ObjectId id) {
        return repository.findById(id);
    }

    public List<MongoPost> get_data2(ObjectId id) {
        Query query = Query.query(Criteria.where("_id").is(id));
        return mongoTemplate.find(query, MongoPost.class);
    }

    public MongoPost create_data(String title, String content) {
        MongoPost post = repository.insert(new MongoPost(title, content));
        return post;
    }
    public MongoPost create_data2(String title, String content) {
        MongoPost post = new MongoPost(title, content);
        repository.save(post);
        return post;
    }


    public MongoPost create_data3(String title, String content) {
        MongoPost post = new MongoPost(title, content);
        return mongoTemplate.insert(post);
    }

    public MongoPost update_data(ObjectId id, String title, String content) {
        Optional<MongoPost> tmp = repository.findById(id);

        if(tmp != null) {
            MongoPost post = tmp.get();
            if(title != null) {
                post.setTitle(title);
            }
            if(content != null)
                post.setContent(content);

            repository.save(post);

            return post;
        }
        return null;

    }

    public void update_data2(ObjectId id, String title, String content) {
        //특정 컬렉션에 대한 쿼리 작성
        Query query = Query.query(Criteria.where("_id").is(id));
        //update할 필드와 값 설정
        Update update = new Update().update("title", title).update("content", content);
        //update 실행
        mongoTemplate.updateFirst(query, update, MongoPost.class);
    }

    public void delete_data(ObjectId id) {
        repository.deleteById(id);
    }

    public List<mongoChild> all_child(MongoPost parent) {
        return parent.getChildren();
    }

    //기존 repository만 이용한 update child를 전부 읽고 수정 후 다 저장하느 방식이라 속도가 느리다
    public void create_child(ObjectId parentId, String data) {
        Optional<MongoPost> tmp = repository.findById(parentId);

        if(tmp != null) {
            MongoPost post = tmp.get();
            mongoChild tmpC = new mongoChild(data);
            repositoryC.save(tmpC);
            List<mongoChild> tmpL = post.getChildren();
            tmpL.add(tmpC);
            post.setChildren(tmpL);
            repository.save(post);
        }
    }

    //query를 따로 지정하여 만든 update 속도가 빠르다
    public mongoChild create_child2(ObjectId parentId, String data) {
        Query query = Query.query(Criteria.where("_id").is(parentId));

        mongoChild child = repositoryC.save(new mongoChild(data));

        Update update = new Update().push("children", child);

        MongoPost update_test = mongoTemplate.findAndModify(query, update, MongoPost.class, "test");

        if(update_test == null) {
            System.out.println("no MongoPost error");
        }
        return child;
    }

    //criteria로 한 번 해봤다
    public void create_child3(ObjectId parentId, String data) {

    }

    public void delete_child(ObjectId parentId, ObjectId childId) {
        Query query = Query.query(Criteria.where("_id").is(parentId));
        Update update = new Update().pull("children", childId);
        mongoTemplate.updateFirst(query, update, Transaction.class, "test");
        if(childId != null)
            repositoryC.deleteById(childId);
    }

}
