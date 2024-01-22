package com.example.demo.mySQL;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class mySQLService {

    @Autowired
    private final mySQLRepository repo;

    @Autowired
    private final sqlChildRepository repoC;

    public sqlEntity create_data(String title, String data) {
        sqlEntity entity = repo.save(new sqlEntity(title, data));
        return entity;
    }

    public List<sqlEntity> all_data() {
        return repo.findAll();
    }

    public sqlEntity update_data(Long id, String title, String data) {
        Optional<sqlEntity> tmp = repo.findById(id);
        
        if(tmp != null) {
            sqlEntity test = tmp.get();
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

    public Optional<sqlEntity> get_data(Long id) {
        return repo.findById(id);
    }

    public void create_child(Long id, String data) {
        Optional<sqlEntity> entity = repo.findById(id);
        repoC.save(new sqlChild(entity.get(), data));
    }

    public List<sqlChild> getChilds(Long id) {
        Optional<sqlEntity> entity = repo.findById(id);
        return entity.get().getChilds();
    }

    public sqlChild update_child(Long id, String data) {
        Optional<sqlChild> tmp = repoC.findById(id);
        sqlChild child = tmp.get();
        child.setData(data);
        return repoC.save(child);
    }

    public void delete_child(Long child_id) {
        if(child_id != null)
            repoC.deleteById(child_id);
    }
}
