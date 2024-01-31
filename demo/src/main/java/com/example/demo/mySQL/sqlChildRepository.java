package com.example.demo.mySQL;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

public interface sqlChildRepository extends JpaRepository<sqlChild, Long> {
    
}
