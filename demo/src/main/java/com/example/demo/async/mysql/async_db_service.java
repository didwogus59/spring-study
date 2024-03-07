package com.example.demo.async.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class async_db_service {

    @Autowired
    private final async_db_repository repo;

    @Qualifier("asyncTemplate")
    @Autowired
    private JdbcTemplate template;

    @Async
    public void create_data1(String data) {
        repo.save(new async_entity(data));
    }

    public void create_data2(String data) {
        repo.save(new async_entity(data));
    }


    public List<async_entity> all_data() {
        return repo.findAll();
    }

    @Async
    public void update_data(Long id, String data) {
        Optional<async_entity> tmp = repo.findById(id);
    }

    @Async
    public void delete_data(Long id) {
        repo.deleteById(id);
    }

    public Optional<async_entity> get_data(Long id) {
        return repo.findById(id);
    }
}
