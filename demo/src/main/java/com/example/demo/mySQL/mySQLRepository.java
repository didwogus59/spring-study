package com.example.demo.mySQL;

import javax.sql.DataSource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;

@Repository
public interface mySQLRepository extends JpaRepository<sqlEntity, Long> {
}

