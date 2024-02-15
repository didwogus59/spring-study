package com.example.demo.elasticSearch;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;

@Service
public class elasticService {
    
    @Autowired
    elasticRepository repo;

    @Autowired
    ElasticsearchClient client;

    public void save_data(my_entity entity) {
        repo.save(entity);
        
    }

    public void save_data2(my_entity entity) {
        try {
        client.index(i -> i
        .index("my_index")
        .id(entity.getId())
        .document(entity));
        } catch(Exception e) {
            
        }
    }
    public my_entity get_data(String id) {
        Optional<my_entity> entity = repo.findById(id);
        return entity.get();
    }

    public my_entity get_data2(String id) { 
        my_entity entity = null;  
        try {
            GetResponse<my_entity> res = client.get(g-> g
            .index("my_index")
            .id(id),
            my_entity.class);
            entity = res.source();
        } catch(Exception e) {
                
        }
        return entity;    
    }

    public void update_data(String id, String name) {
        if(get_data(id) != null) {
            repo.save(new my_entity(id, name));
        }
    }

    public void update_data2(String id, String name) {
        try {
            client.update(u -> u
                .index("my_index")
                .id(id)
                .doc(new my_entity(id, name)),
                my_entity.class
            );
        } catch(Exception e) {

        }
    }

    public void delete_data(String id) {
        repo.deleteById(id);
    }

    public void delete_data2(String id) {
        try {
        client.delete(d -> d
            .index("my_index")
            .id(id));
        } catch(Exception e) {
            
        }
    }
}
