package com.example.demo.elasticSearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.elastic.clients.elasticsearch.ElasticsearchClient;

@SpringBootTest
public class elasticServiceTest {
    
    @Autowired
    elasticService service;
    
    @Test
    void testTestSave() {
        service.save_data2(new my_entity("test2", "hello?"));
    }

    @Test
    void testget() {
        String id = "test2";
        String name = service.get_data(id).getName();
        assertEquals("hello?", name);
    }

    @Test
    void testget2() {
        String id = "test2";
        String name = service.get_data2(id).getName();
        assertEquals("hello?", name);
    }

    @Test
    void updatetest() {
        String id = "test2";
        String name = "www";
        service.update_data(id, name);
        String new_name = service.get_data(id).getName();
        assertEquals("www", new_name);
    }

    
    @Test
    void updatetest2() {
        String id = "test2";
        String name = "qwe";
        service.update_data2(id, name);
        String new_name = service.get_data(id).getName();
        assertEquals("qwe", new_name);
    }

    @Test
    void delete() {
        service.delete_data("test2");
    }

    @Test
    void delete2() {
        service.delete_data2("test2");
    }

}
