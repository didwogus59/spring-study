package com.example.demo.mongodb;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.test;

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

    public List<test_db> all_data() {
        return repository.findAll();
    }


    public List<test_db> all_data2() {
        return mongoTemplate.findAll(test_db.class);
    }

    public Optional<test_db> get_data(ObjectId id) {
        return repository.findById(id);
    }

    public List<test_db> get_data2(ObjectId id) {
        Query query = Query.query(Criteria.where("_id").is(id));
        return mongoTemplate.find(query, test_db.class);
    }

    public test_db create_data(String title, String data) {
        test_db test = repository.insert(new test_db(title, data));
        return test; 
    }
    public test_db create_data2(String title, String data) {
        test_db test = new test_db(title, data);
        repository.save(test);
        return test; 
    }

    
    public test_db create_data3(String title, String data) {
        test_db test = new test_db(title, data);
        return mongoTemplate.insert(test); 
    }

    public test_db update_data(ObjectId id, String title, String data) {
        Optional<test_db> tmp = repository.findById(id);
        
        if(tmp != null) {
            test_db test = tmp.get();
            if(title != null) {
                test.setTitle(title);
            }
            if(data != null)
                test.setData(data);

            repository.save(test);
            
            return test;
        }
        return null;
        
    }

    public void update_data2(ObjectId id, String title, String data) {
        //특정 컬렉션에 대한 쿼리 작성
        Query query = Query.query(Criteria.where("_id").is(id));
        //update할 필드와 값 설정
        Update update = new Update().update("title", title).update("data", data);
        //update 실행
        mongoTemplate.updateFirst(query, update, test_db.class);
    }

    public void delete_data(ObjectId id) {
        repository.deleteById(id);
    }

    public List<mongoChild> all_child(test_db parent) {
        return parent.getChilds();
    }

    //기존 repository만 이용한 update child를 전부 읽고 수정 후 다 저장하느 방식이라 속도가 느리다
    public void create_child(ObjectId parentId, String data) {
        Optional<test_db> tmp = repository.findById(parentId);

        if(tmp != null) {
            test_db test = tmp.get();
            mongoChild tmpC = new mongoChild(data);
            repositoryC.save(tmpC);
            List<mongoChild> tmpL = test.getChilds();
            tmpL.add(tmpC);
            test.setChilds(tmpL);
            repository.save(test);
        }
    }

    //query를 따로 지정하여 만든 update 속도가 빠르다
    public mongoChild create_child2(ObjectId parentId, String data) {
        Query query = Query.query(Criteria.where("_id").is(parentId));
        
        mongoChild child = repositoryC.save(new mongoChild(data));

        Update update = new Update().push("childs", child);
        
        test_db update_test = mongoTemplate.findAndModify(query, update, test_db.class, "test");

        if(update_test == null) {
            System.out.println("no test_db error");
        }
        return child;
    }

    //criteria로 한 번 해봤다
    public void create_child3(ObjectId parentId, String data) {
        
    }

    public void delete_child(ObjectId parentId, ObjectId childId) {
        Query query = Query.query(Criteria.where("_id").is(parentId));
        Update update = new Update().pull("childs", childId);
        mongoTemplate.updateFirst(query, update, Transaction.class, "test");
        if(childId != null)
            repositoryC.deleteById(childId);
    }

    
}
