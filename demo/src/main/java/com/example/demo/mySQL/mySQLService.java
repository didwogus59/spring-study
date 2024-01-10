package com.example.demo.mySQL;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.db_test.test_db;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class mySQLService {

    @Autowired
    private final mySQLRepository repo;

    public void create_data(String title, String data) {
        repo.save(new mySQLEntity(title, data));
    }

    public List<mySQLEntity> all_data() {
        return repo.findAll();
    }

    public mySQLEntity update_data(Long id, String title, String data) {
        Optional<mySQLEntity> tmp = repo.findById(id);
        
        if(tmp != null) {
            mySQLEntity test = tmp.get();
            if(title != null) {
                test.setTitle(title);
            }
            if(data != null)
                test.setData(data);

            repo.save(test);
            
            return test;
        }
        return null;
        
    }

    public void delete_data(Long id) {
        repo.deleteById(id);
    }

    public Optional<mySQLEntity> get_data(Long id) {
        return repo.findById(id);
    }
}
