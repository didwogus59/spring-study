// package com.example.demo.mySQL;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

// import java.util.List;
// import java.util.Optional;

// @DataJpaTest
// public class testbyJpaTest {

//     @Autowired
//     mySQLRepository repo;
    
//     @Autowired
//     sqlChildRepository repoC;
    
//     @Test
//     void testCreate_child() {
//         sqlEntity test = repo.save(new sqlEntity("123423", "123234"));
//         Optional<sqlEntity> entity = repo.findById(test.getId());
//         repoC.save(new sqlChild(entity.get(), "examplasde"));
//     }

//     @Test
//     void testGet_child() {
//         Optional<sqlEntity> entity = repo.findById((long)1);
//         List<sqlChild> child = entity.get().getChilds(); 
//         for(int i = 0; i < child.size(); i++) {
//             System.out.println(child.get(i).getData());
//         }
//     }
// }
