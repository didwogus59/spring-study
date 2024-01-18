package com.example.demo.mySQL;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

@DataJpaTest
public class mySQLServiceTest {

    @Autowired
    mySQLRepository repo;
    
    @Autowired
    sqlChildRepository repoC;
    
    @Test
    void testCreate_child() {
        sqlEntity test = repo.save(new sqlEntity("1234", "1234"));
        Optional<sqlEntity> entity = repo.findById(test.getId());
        repoC.save(new sqlChild(entity.get(), "example"));
    }

    @Test
    void testGet_child() {

    }
}
