package com.example.demo.mySQL;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SqlService {

    @Autowired
    private final SqlRepository repo;

    @Autowired
    private final SqlChildRepository repoC;

    @Qualifier("myTemplate")
    private JdbcTemplate template;

    // @Qualifier("mySQLEM")
    // @Autowired
    // EntityManager em;

    // public void test_em() {
    //     em.createQuery("select data from sql_entity");
    // }

    public SqlEntity create_data(String title, String content) {
        SqlEntity entity = repo.save(new SqlEntity(title, content));
        return entity;
    }

    public List<SqlEntity> all_data() {
        return repo.findAll();
    }

    public SqlEntity update_data(Long id, String title, String content) {
        Optional<SqlEntity> tmp = repo.findById(id);

        if(tmp != null) {
            SqlEntity entity = tmp.get();
            if(title != null) {
                entity.setTitle(title);
            }
            if(content != null)
                entity.setContent(content);

            repo.save(entity);

            return entity;
        }
        return null;

    }

    public void delete_data(Long id) {
        repo.deleteById(id);
    }

    public Optional<SqlEntity> get_data(Long id) {
        return repo.findById(id);
    }

    public void create_child(Long id, String content) {
        Optional<SqlEntity> entity = repo.findById(id);
        repoC.save(new SqlChild(entity.get(), content));
    }

    @Transactional
    public List<SqlChild> getChilds(Long id) {
        Optional<SqlEntity> entity = repo.findById(id);
        return entity.get().getChildren();
    }

    public SqlChild update_child(Long id, String content) {
        Optional<SqlChild> tmp = repoC.findById(id);
        SqlChild child = tmp.get();
        child.setContent(content);
        return repoC.save(child);
    }

    public void delete_child(Long childId) {
        if(childId != null)
            repoC.deleteById(childId);
    }
}
