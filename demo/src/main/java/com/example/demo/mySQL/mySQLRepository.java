package com.example.demo.mySQL;

import org.springframework.data.jpa.repository.JpaRepository;

public interface mySQLRepository extends JpaRepository<mySQLEntity, Long> {
    
}
