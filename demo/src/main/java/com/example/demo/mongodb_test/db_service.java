package com.example.demo.mongodb_test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class db_service {
    @Autowired
    private db_repository repository;

    @Autowired
    private child_repository repositoryC;

    public List<test_db> all_data() {
        return repository.findAll();
    }

    public Optional<test_db> get_data(ObjectId id) {
        return repository.findById(id);
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

    public void delete_data(ObjectId id) {
        repository.deleteById(id);
    }

    public List<child> all_child(test_db parent) {
        return parent.getChilds();
    }

    public void create_child(ObjectId parentId, String data) {
        Optional<test_db> tmp = repository.findById(parentId);
        if(tmp != null) {
            test_db test = tmp.get();
            child tmpC = new child(data);
            repositoryC.save(tmpC);
            //test.getChilds().add(new child(data));
            List<child> tmpL = test.getChilds();
            tmpL.add(tmpC);
            test.setChilds(tmpL);
            repository.save(test);
        }
    }
}
